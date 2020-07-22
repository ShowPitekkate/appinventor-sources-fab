package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Mock Sidebar component.
 *
 * @author singhalsara48@gmail.com (Sara Singhal)
 */
public class MockSidebar extends MockContainer {
    public static final String TYPE = "Sidebar";
    private AbsolutePanel menuWidget;

    //whether the mock sidebar is opened or closed.
    private boolean open;

    // whether the mock sidebar is openable (false when Action Bar is absent)
    private boolean enabled;

    /**
     * Creates a new MockSidebar component.
     *
     * @param editor  editor of source file the component belongs to
     */
    public MockSidebar(SimpleEditor editor) {
        super(editor, TYPE, images.sidebar(), new MockHVLayout(ComponentConstants.LAYOUT_ORIENTATION_VERTICAL));

        rootPanel.setHeight("100%");
        menuWidget = new AbsolutePanel();
        menuWidget.setStylePrimaryName("ode-SimpleMockContainer");
        menuWidget.addStyleName("ode-SimpleMockFormSidebar");
        menuWidget.add(rootPanel);

        initComponent(menuWidget);
    }

    @Override
    int getHeightHint() {
        int heightHint = super.getHeightHint();
        if (heightHint == LENGTH_PREFERRED) {
            heightHint = getForm().usableScreenHeight;
        }
        return heightHint;
    }

    @Override
    public boolean isSidebar() {
        return true;
    }

    /**
     * Whether the sidebar is shown in designer.
     *
     * @return {@code true} iff sidebar is visible
     */
    public boolean isOpen() {
        return open;
    }

    @Override
    protected boolean isPropertyVisible(String propertyName) {
        if (propertyName.equals(PROPERTY_NAME_WIDTH) ||
                propertyName.equals(PROPERTY_NAME_HEIGHT)) {
            return false;
        }
        return super.isPropertyVisible(propertyName);
    }

    @Override
    protected void onSelectedChange(boolean selected) {
        super.onSelectedChange(selected);
        if (selected && !open) {
            toggle();
        }
    }

    /**
     * Set the mock sidebar's enabled property.
     *
     * @param enabled  {@code true} iff sidebar can be opened
     */
    public void setEnabled(boolean enabled) {
        if (!enabled && open) {
            toggle();
        }
        this.enabled = enabled;
    }

    /**
     * Toggle (open or close) the mock sidebar; has no effect when sidebar is disabled.
     */
    public void toggle() {
        if (enabled) {
            open = !open;
            refreshForm();
        }
    }
}
