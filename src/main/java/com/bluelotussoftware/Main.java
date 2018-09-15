/*
 * Copyright 2018 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bluelotussoftware;

import io.netty.channel.Channel;
import java.io.IOException;

/**
 * Main class.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0.0
 */
public class Main {

    public static final String BASE_URI_HTTP = "http://localhost:8080/";
    public static final String BASE_URI_HTTP2 = "http://localhost:8081/";

    public static void main(String[] args) throws IOException {
        final Channel httpServer = NettyServerProviders.initializeNettyHttpServer(BASE_URI_HTTP, HelloResource.class);
        final Channel http2Server = NettyServerProviders.initializeNettyHttp2Server(BASE_URI_HTTP2, HelloResource.class);

        System.out.println(String.format("Jersey Netty Servers started with WADL available at "
                + "%sapplication.wadl\n or %sapplication.wadl\n Hit enter to stop it...", BASE_URI_HTTP, BASE_URI_HTTP2));
        System.in.read();

        httpServer.close();
        http2Server.close();
    }
}
