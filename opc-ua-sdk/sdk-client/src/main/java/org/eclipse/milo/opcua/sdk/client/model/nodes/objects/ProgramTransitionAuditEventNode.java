/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramTransitionAuditEventType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ProgramTransitionAuditEventNode extends AuditUpdateStateEventNode implements ProgramTransitionAuditEventType {
    public ProgramTransitionAuditEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<FiniteTransitionVariableNode> getTransitionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "Transition").thenApply(FiniteTransitionVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getTransition() {
        return getTransitionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setTransition(LocalizedText value) {
        return getTransitionNode().thenCompose(node -> node.setValue(value));
    }
}
