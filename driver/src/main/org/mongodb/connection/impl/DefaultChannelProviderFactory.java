/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.connection.impl;

import org.mongodb.MongoCredential;
import org.mongodb.connection.BufferProvider;
import org.mongodb.connection.ChannelProvider;
import org.mongodb.connection.ServerAddress;
import org.mongodb.connection.StreamFactory;

import java.util.List;

class DefaultChannelProviderFactory implements ChannelProviderFactory {
    private final ChannelProviderSettings settings;
    private final StreamFactory streamFactory;
    private final List<MongoCredential> credentialList;
    private final BufferProvider bufferProvider;

    public DefaultChannelProviderFactory(final ChannelProviderSettings settings, final StreamFactory streamFactory,
                                         final List<MongoCredential> credentialList, final BufferProvider bufferProvider) {
        this.settings = settings;
        this.streamFactory = streamFactory;
        this.credentialList = credentialList;
        this.bufferProvider = bufferProvider;
    }

    @Override
    public ChannelProvider create(final ServerAddress serverAddress) {
        return new DefaultChannelProvider(serverAddress, new DefaultConnectionFactory(streamFactory, bufferProvider, credentialList),
                settings);
    }
}
