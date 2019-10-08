package cn.dutyujm.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/{tid}")
@Component
public class WebSocket {


    public static Map<String, List<Session>> electricSocketMap = new ConcurrentHashMap<String, List<Session>>();//定义一个静态map存储所有已连接用户，键值对是Tid:List<Session>


    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("tid") String tid, Session session) { //建立新连接时调用
        List<Session> sessions = electricSocketMap.get(tid); //从map中找一个要创建连接的tid的已经建立的会话

        if(null==sessions){                                     //如果找到的会话是空，那么说明这个tid当前没有建立任何的连接
            List<Session> sessionList = new ArrayList<>();      //新建一个临时List，我们暂且称之为会话链，如果一个用户只有一次连接，那么会话链有一个会话，如果用户从比如不同的电脑同时连接了，会话该用户的会话链就将有两个会话
            sessionList.add(session);                           //将即将新建的会话存入List中
            electricSocketMap.put(tid,sessionList);             //把list存入map
        }else{
            sessions.add(session);                              //如果要新建连接的tid已经有会话了，那么就在该tid的连接session会话链中加入又一个新的会话
        }

        /**
         * 上述连接if...else...用于判断该用户是否已经建立了会话，处理同时从两台电脑登录了账户，或者从不同浏览器登录的情况
         * 也就是说为什么map中不直接存session而是要存List<Session>，就是为了处理同用户多次连接
         */
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("tid") String tid,Session session) {
        if (electricSocketMap.containsKey(tid)){
            electricSocketMap.get(tid).remove(session);//移除所需关闭对话的对话链
        }
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        /**
         * 群聊功能
         */
        System.out.println("websocket received message:"+message+ "\t By"+ session.getId() );//在控制台输出后端所接受到的信息

        for(String key : electricSocketMap.keySet()) {  //for循环，每次循环获取一个map的key值，也就是说对每一个用户发送消息
        try {
            List<Session> sessionList = electricSocketMap.get(key);  //获取key值对应的用户的会话链

            for(Session session1 : sessionList){
                session1.getBasicRemote().sendText(message);      //向所选用户的每个会话都发送信息（该用户要是同时在两个地方）
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        }
    }


    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");;
    }
}
