/*
 * Copyright 2014 Tariq Bugrara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.progix.dropwizard.patch;

import io.progix.dropwizard.patch.operations.contextual.json.JsonAddOperation;

import java.util.List;

public class DefaultJsonPatch<T> extends ContextualJsonPatch<T> {

    /**
     * Constructs an instance using a list of {@link JsonPatchOperation}
     *
     * @param instructions A list of {@link JsonPatchOperation}
     */
    public DefaultJsonPatch(List<JsonPatchOperation> instructions) {
        super(instructions);
        setAdd(new JsonAddOperation<T>());
    }
}
