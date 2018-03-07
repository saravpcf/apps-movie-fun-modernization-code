package org.superbiz.moviefun.blobstore;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Optional;

public interface BlobStore {

    void put(Blob blob) throws IOException;

    Optional<Blob> get(String name) throws IOException;
}
