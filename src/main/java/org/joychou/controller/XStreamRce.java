package org.joychou.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.joychou.dao.User;
import org.joychou.util.WebUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class XStreamRce {

    /**
     * Fix method: update xstream to 1.4.11
     * Xstream affected version: 1.4.10 or <= 1.4.6
     * Set Content-Type: application/xml
     *
     * @author JoyChou @2019-07-26
     */
    @PostMapping("/xstream")
    public String parseXml(HttpServletRequest request) throws Exception {
        String xml = WebUtils.getRequestBody(request);
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY); // This will cause all XStream versions to be affected.
        xstream.fromXML(xml);
        return "xstream";
    }

}
