package edu.escuelaing.mcsw.frameworkNiuBank;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

public interface FrameworkNiuBank {


    String handle(String path, String[] request, HttpResponse response);

}
