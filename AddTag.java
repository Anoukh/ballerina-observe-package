/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.nativeimpl.observe;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BStruct;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.Receiver;
import org.ballerinalang.observe.trace.OpenTracerBallerinaWrapper;

/**
 * This function adds tags to a span.
 */
@BallerinaFunction(
        packageName = "ballerina.observe",
        functionName = "addTag",
        receiver = @Receiver(type = TypeKind.STRUCT, structType = "Span", structPackage = "ballerina.observe"),
        args = {@Argument(name = "tagKey", type = TypeKind.STRING),
                @Argument(name = "tagValue", type = TypeKind.STRING)},
        isPublic = true
)
public class AddTag extends AbstractNativeFunction {
    @Override
    public BValue[] execute(Context context) {
        BStruct span = (BStruct) getRefArgument(context, 0);
        String spanId = span.getStringField(0);
        String tagKey = getStringArgument(context, 0);
        String tagValue = getStringArgument(context, 1);
        OpenTracerBallerinaWrapper.getInstance().addTags(spanId, tagKey, tagValue);
        return VOID_RETURN;
    }
}
