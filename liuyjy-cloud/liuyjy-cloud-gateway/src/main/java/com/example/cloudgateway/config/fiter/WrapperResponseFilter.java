package com.example.cloudgateway.config.fiter;

import org.springframework.stereotype.Component;

//import org.gateway.response.Response;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/8 11:57
 * @Description: 统一返回报文格式
 */
@Component
public class WrapperResponseFilter {  //implements GlobalFilter, Ordered {
    /**
     *需要注意的是order需要小于-1，需要先于NettyWriteResponseFilter过滤器执行
     * @return
     */
  /*  @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        // 释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String rs = new String(content, Charset.forName("UTF-8"));
                       *//* Response response = new Response();
                        response.setCode("1");
                        response.setMessage("请求成功");
                        response.setData(rs);*//*

                        byte[] newRs = JSON.toJSONString(rs).getBytes(Charset.forName("UTF-8"));
                        originalResponse.getHeaders().setContentLength(newRs.length);//如果不重新设置长度则收不到消息。
                        return bufferFactory.wrap(newRs);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }*/
}
