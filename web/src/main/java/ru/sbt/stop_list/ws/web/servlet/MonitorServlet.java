package ru.sbt.stop_list.ws.web.servlet;

import com.sbt.stop_list.ws.MessageList;
import com.sbt.stop_list.ws.ejb.HistoryService;
import com.sbt.stop_list.ws.jms.MessageSender;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;


@WebServlet(urlPatterns = "/monitor")
public class MonitorServlet extends HttpServlet {
    @EJB
    private HistoryService historyService;
    @EJB
    private MessageSender messageSender;
    @EJB
    private MessageList messageList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        messageSender.sentMessage(" test message "+(new Date()));
        resp.setContentType("text/html");


        messageSender.sendMessage("message from servlet at "+(new Date()));

        Writer writer = resp.getWriter();

        writer.write("<html lang=\"en\">"
                + "<head><title>Sin's servlet</title></head>");

        writer.write("Hello world! >> now is"+(new Date()));

        writer.write("  <br><B> HistoryService is "+(historyService==null?" is NULL ":historyService.checkEM() )+" </B><br>");
        writer.write("  <br><B> MesageSender is "+(messageSender==null?" is NULL ":messageSender.checkBean() )+" </B><br>");
        writer.write("  <br>Messages</B><br>");

        for(String message : messageList){
            writer.write("  <br>"+message);
        }
        writer.write("  <br>end</B><br>");


        writer.write("End of properties "+(new Date()));
        writer.write("</body></html>");
        writer.close();
    }


}
