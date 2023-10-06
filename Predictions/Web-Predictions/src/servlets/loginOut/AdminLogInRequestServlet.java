package servlets.loginOut;

import dto.loginOut.LoginResponseDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.usersManager.api.UserManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "AdminLogInRequestServlet", urlPatterns = "/loginOut/loginAdmin")
public class AdminLogInRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = request.getParameter("simulationRequestBody");
        String userName = GSON_INSTANCE.fromJson(requestBody, String.class);
        UserManager userManager = PredictionsServletUtils.getUserManager(getServletContext());
        LoginResponseDto loginResponseDto = userManager.loginAdminRequest(userName);

        String jsonResp = GSON_INSTANCE.toJson(loginResponseDto);
        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }
}
