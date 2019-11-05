package com.impubudu.exercise4.listeners;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class CustomChunkListener {
    @BeforeChunk
    public void beforeChunk(ChunkContext context){
        System.out.println("Before Chunk");
    }

    @AfterChunk
    public void afterChunk(ChunkContext context){
        System.out.println("After Chunk");
    }

    @AfterChunkError
    public void afterFailedChunk(ChunkContext context){
        System.out.println("After Chunk Error");
    }
}
