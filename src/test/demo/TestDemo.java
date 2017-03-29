package demo;

import com.demo.RecevieMsg;
import com.demo.SendMsg;
import org.junit.Test;

/**
 * Created by wangc on 2017/3/29.
 */
public class TestDemo {

    private static final String BROKER_URL = "tcp://192.168.0.15:61616";

    @Test
    public void sendMsgMethod(){
        SendMsg send = new SendMsg(BROKER_URL);
        send.sendMsg("hello world");
    }

    @Test
    public void recevieMsgMethod(){
        RecevieMsg recevieMsg = new RecevieMsg(BROKER_URL);
        recevieMsg.recevieMsg();
    }
}
