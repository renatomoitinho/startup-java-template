package demo.pipeline.ecs.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class NetworkInfo {

    public String getIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ignore) {
            return StringUtils.EMPTY;
        }
    }

    public String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ignore) {
            return StringUtils.EMPTY;
        }
    }
}
