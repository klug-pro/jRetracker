package org.jretracker;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Retracker pipeline factory.
 */
public class RetrackerServerPipelineFactory implements ChannelPipelineFactory {
    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = pipeline();

        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("handler", new RetrackerServerHandler());
        pipeline.addLast("encoder", new HttpResponseEncoder());

        return pipeline;
    }
}
