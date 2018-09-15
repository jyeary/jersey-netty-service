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
import java.net.URI;
import org.glassfish.jersey.netty.connector.NettyConnectorProvider;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * A basic implementation of {@link NettyConnectorProvider} providing HTTP and HTTP/2 servers for the {@link URI} and
 * resource classes provided.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0.0
 */
public final class NettyServerProviders {

    /**
     * Initializes a {@link NettyHttpContainerProvider} using the provided {@link URI} and resource classes with HTTP
     * 1.1.
     *
     * @param baseURI The base URI to connect to the server.
     * @param classes The resource classes to provide serve using this provider.
     * @return A initialized Netty {@link Channel}.
     */
    public static Channel initializeNettyHttpServer(final String baseURI, final Class<?>... classes) {
        final ResourceConfig rc = new ResourceConfig(classes);
        return NettyHttpContainerProvider.createServer(URI.create(baseURI), rc, false);
    }

    /**
     * Initializes a {@link NettyHttpContainerProvider} using the provided {@link URI} and resource classes with HTTP/2.
     *
     * @param baseURI The base URI to connect to the server.
     * @param classes The resource classes to provide serve using this provider.
     * @return A initialized Netty {@link Channel}.
     */
    public static Channel initializeNettyHttp2Server(final String baseURI, final Class<?>... classes) {
        final ResourceConfig rc = new ResourceConfig(classes);
        return NettyHttpContainerProvider.createHttp2Server(URI.create(baseURI), rc, null);
    }

}
