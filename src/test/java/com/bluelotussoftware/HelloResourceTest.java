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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HelloResourceTest {

    private Channel httpServer;
    private Channel http2Server;
    private WebTarget httpTarget;
    private WebTarget http2Target;

    @Before
    public void setUp() throws Exception {
        httpServer = NettyServerProviders.initializeNettyHttpServer(Main.BASE_URI_HTTP, HelloResource.class);
        http2Server = NettyServerProviders.initializeNettyHttp2Server(Main.BASE_URI_HTTP2, HelloResource.class);
        Client httpClient = ClientBuilder.newClient();
        Client http2Client = ClientBuilder.newClient();
        httpTarget = httpClient.target(Main.BASE_URI_HTTP);
        http2Target = http2Client.target(Main.BASE_URI_HTTP2);
    }

    @After
    public void tearDown() throws Exception {
        httpServer.close();
        http2Server.close();
    }

    /**
     * Test to see that the message
     * {@literal "Hello World from Jersey on Netty!"} is sent in the response.
     */
    @Test
    public void testHelloHttp() {
        String responseMsg = httpTarget.path("/").request().get(String.class);
        assertEquals("Hello World from Jersey on Netty!", responseMsg);
    }

    /**
     * Test to see that the message
     * {@literal "Hello World from Jersey on Netty!"} is sent in the response.
     */
    @Test
    public void testHelloHttp2() {
        String responseMsg = http2Target.path("/").request().get(String.class);
        assertEquals("Hello World from Jersey on Netty!", responseMsg);
    }

}
