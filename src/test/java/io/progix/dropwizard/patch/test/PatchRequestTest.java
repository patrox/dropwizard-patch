package io.progix.dropwizard.patch.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.progix.dropwizard.patch.explicit.PatchInstruction;
import io.progix.dropwizard.patch.explicit.PatchOperation;
import io.progix.dropwizard.patch.explicit.PatchRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

public class PatchRequestTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        PatchInstruction add = new PatchInstruction(PatchOperation.ADD, "/a/b/c",
                new ArrayList<Object>(Arrays.asList(42)), null);
        PatchInstruction addElement = new PatchInstruction(PatchOperation.ADD, "/a/b/1",
                new ArrayList<Object>(Arrays.asList(42)), null);

        PatchInstruction replace = new PatchInstruction(PatchOperation.REPLACE, "/a/b/c",
                new ArrayList<Object>(Arrays.asList(42)), null);

        PatchInstruction remove = new PatchInstruction(PatchOperation.REMOVE, "/a/b/c", null, null);
        PatchInstruction removeElement = new PatchInstruction(PatchOperation.REMOVE, "/a/b/1", null, null);

        PatchInstruction test = new PatchInstruction(PatchOperation.TEST, "/a/b/c",
                new ArrayList<Object>(Arrays.asList("C", "D")), null);

        PatchRequest request = new PatchRequest(Arrays.asList(replace, test));
        assertThat(MAPPER.readValue(fixture("fixtures/patchrequest.json"), PatchRequest.class)).isEqualTo(request);
    }
}
