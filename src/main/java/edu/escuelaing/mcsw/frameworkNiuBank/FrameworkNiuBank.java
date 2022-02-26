package edu.escuelaing.mcsw.frameworkNiuBank;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONObject;

public interface FrameworkNiuBank {


     JSONObject handle(String path, String request, HttpResponse response);

}
