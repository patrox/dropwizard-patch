package io.progix.dropwizard.patch.explicit;

import com.google.common.base.Preconditions;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.model.AbstractResourceMethod;
import com.sun.jersey.spi.container.ResourceMethodDispatchProvider;
import com.sun.jersey.spi.dispatch.RequestDispatcher;
import io.dropwizard.jersey.PATCH;

public class PatchResourceDispatchProvider implements ResourceMethodDispatchProvider {

    private final ResourceMethodDispatchProvider provider;

    public PatchResourceDispatchProvider(ResourceMethodDispatchProvider provider) {
        this.provider = Preconditions.checkNotNull(provider);
    }

    @Override
    public RequestDispatcher create(AbstractResourceMethod method) {
        RequestDispatcher dispatcher = provider.create(method);
        if (dispatcher == null) {
            return null;
        }

        if (method.getMethod().isAnnotationPresent(PATCH.class)) {
            //			dispatcher = new PatchHookDispatcher();
        }

        return dispatcher;
    }

    public static class PatchHookDispatcher implements RequestDispatcher {

        private final RequestDispatcher dispatcher;
        private final PatchInstruction request;

        public PatchHookDispatcher(RequestDispatcher dispatcher, PatchInstruction request) {
            this.dispatcher = dispatcher;
            this.request = request;
        }

        @Override
        public void dispatch(Object resource, HttpContext context) {
            try {
                dispatcher.dispatch(resource, context);
            } finally {
                // get patchrequest and call its do function
            }
        }
    }
}
