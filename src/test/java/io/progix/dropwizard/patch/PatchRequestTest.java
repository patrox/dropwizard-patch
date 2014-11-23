package io.progix.dropwizard.patch;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.progix.dropwizard.patch.hooks.PatchInstruction;
import io.progix.dropwizard.patch.hooks.PatchOperation;
import io.progix.dropwizard.patch.hooks.PatchRequest;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

public class PatchRequestTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        PatchInstruction replace = new PatchInstruction(PatchOperation.REPLACE, "/a/b/c", new ArrayList<Object>(Arrays
                .asList(42)), null);
        PatchInstruction test = new PatchInstruction(PatchOperation.TEST, "/a/b/c", new ArrayList<Object>(Arrays
                .asList("C")), null);
        PatchRequest request = new PatchRequest(Arrays.asList(replace, test));
        assertThat(MAPPER.readValue(fixture("fixtures/patchrequest.json"), PatchRequest.class))
                .isEqualTo(request);
    }
}