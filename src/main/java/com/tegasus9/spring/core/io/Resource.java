package com.tegasus9.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
