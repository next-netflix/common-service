package com.nextnetflix.commonservice.configurations;

import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class CommonGrpcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ManagedChannelBuilder<?> defaultChannelBuilder() {
        // default values, services override host/port
        return ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext();
    }

    @Bean
    @ConditionalOnMissingBean
    public io.grpc.ClientInterceptor defaultGrpcInterceptor() {
        return new io.grpc.ClientInterceptor() {
            @Override
            public <ReqT, RespT> io.grpc.ClientCall<ReqT, RespT> interceptCall(
                    io.grpc.MethodDescriptor<ReqT, RespT> method,
                    io.grpc.CallOptions callOptions,
                    io.grpc.Channel next) {
                // Simply forward the call
                return next.newCall(method, callOptions);
            }
        };
    }


    @Bean
    @ConditionalOnMissingBean
    public ManagedChannel defaultChannel(
            ManagedChannelBuilder<?> builder,
            ClientInterceptor interceptor
    ) {
        return builder.intercept(interceptor).build();
    }
}