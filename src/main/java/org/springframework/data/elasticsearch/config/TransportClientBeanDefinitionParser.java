/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.data.elasticsearch.config;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.w3c.dom.Element;

/**
 * TransportClientBeanDefinitionParser
 *
 * @author Rizwan Idrees
 * @author Mohsin Husen
 */

public class TransportClientBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(TransportClientFactoryBean.class);
        setConfigurations(element, builder);
        return getSourcedBeanDefinition(builder, element, parserContext);
    }

    private void setConfigurations(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("shieldTransportSsl", element.getAttribute("shield-transport-ssl"));
        builder.addPropertyValue("shieldSslKeystorePath", element.getAttribute("shield-ssl-keystore-path"));
        builder.addPropertyValue("shieldSslKeystorePassword", element.getAttribute("shield-ssl-keystore-password"));
        builder.addPropertyValue("shieldUser", element.getAttribute("shield-user"));
        builder.addPropertyValue("clusterNodes", element.getAttribute("cluster-nodes"));
        builder.addPropertyValue("clusterName", element.getAttribute("cluster-name"));
        builder.addPropertyValue("clientTransportSniff", Boolean.valueOf(element.getAttribute("client-transport-sniff")));
        builder.addPropertyValue("clientIgnoreClusterName", Boolean.valueOf(element.getAttribute("client-transport-ignore-cluster-name")));
        builder.addPropertyValue("clientPingTimeout", element.getAttribute("client-transport-ping-timeout"));
        builder.addPropertyValue("clientNodesSamplerInterval", element.getAttribute("client-transport-nodes-sampler-interval"));
    }

    private AbstractBeanDefinition getSourcedBeanDefinition(BeanDefinitionBuilder builder, Element source,
                                                            ParserContext context) {
        AbstractBeanDefinition definition = builder.getBeanDefinition();
        definition.setSource(context.extractSource(source));
        return definition;
    }
}
