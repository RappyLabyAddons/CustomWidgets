package com.rappytv.widgets.gui.activities;

import com.rappytv.widgets.WidgetAddon;
import com.rappytv.widgets.gui.widgets.GuiWidgetProperties;
import net.labymod.api.client.gui.screen.LabyScreen;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.Links;
import net.labymod.api.client.gui.screen.widget.Widget;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.ScrollWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.VerticalListWidget;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AutoActivity
@Links({@Link("manage.lss"), @Link("overview.lss")})
public class WidgetActivity extends Activity {

    private final WidgetAddon addon;
    private final VerticalListWidget<ListWidget> textReplacementList;
    private final List<ListWidget> widgets;
    private ButtonWidget removeButton;
    private ButtonWidget editButton;
    private FlexibleContentWidget inputWidget;
    private Action action;

    public WidgetActivity(WidgetAddon addon) {
        this.addon = addon;
        this.widgets = new ArrayList<>();
        addon.configuration().getWidgets().forEach((ID, widget) -> this.widgets.add(new ListWidget(widget)));
        this.textReplacementList = new VerticalListWidget<>();
        this.textReplacementList.addId("custom-widget-list");
        this.textReplacementList.setSelectCallback((widget) -> {
            this.editButton.setEnabled(true);
            this.removeButton.setEnabled(true);
        });
        this.textReplacementList.setDoubleClickCallback((widget) -> this.setAction(Action.EDIT));
    }

    public void initialize(Parent parent) {
        super.initialize(parent);
        FlexibleContentWidget container = new FlexibleContentWidget();
        container.addId("custom-widget-container");

        for(ListWidget widget : this.widgets) {
            this.textReplacementList.addChild(widget);
        }

        container.addFlexibleContent(new ScrollWidget(this.textReplacementList));
        ListWidget selectedTextReplacement = this.textReplacementList.session().getSelectedEntry();
        HorizontalListWidget menu = new HorizontalListWidget();
        menu.addId("overview-button-menu");
        menu.addEntry(ButtonWidget.i18n("labymod.ui.button.add", () -> this.setAction(Action.ADD)));
        this.editButton = ButtonWidget.i18n("labymod.ui.button.edit", () -> this.setAction(Action.EDIT));
        this.editButton.setEnabled(Objects.nonNull(selectedTextReplacement));
        menu.addEntry(this.editButton);
        this.removeButton = ButtonWidget.i18n("labymod.ui.button.remove", () -> this.setAction(Action.REMOVE));
        this.removeButton.setEnabled(Objects.nonNull(selectedTextReplacement));
        menu.addEntry(this.removeButton);
        container.addContent(menu);
        this.document().addChild(container);
        if(!Objects.isNull(this.action)) {
            DivWidget manageContainer = new DivWidget();
            manageContainer.addId("manage-container");
            Widget overlayWidget;
            switch (this.action) {
                case ADD:
                default:
                    ListWidget newTextReplacement = new ListWidget(GuiWidgetProperties.createDefault());
                    overlayWidget = this.initializeManageContainer(newTextReplacement);
                    break;
                case EDIT:
                    overlayWidget = this.initializeManageContainer(selectedTextReplacement);
                    break;
                case REMOVE:
                    overlayWidget = this.initializeRemoveContainer(selectedTextReplacement);
            }

            manageContainer.addChild(overlayWidget);
            this.document().addChild(manageContainer);
        }
    }

    private FlexibleContentWidget initializeRemoveContainer(
        ListWidget textReplacementWidget) {
        this.inputWidget = new FlexibleContentWidget();
        this.inputWidget.addId("remove-container");
        ComponentWidget confirmationWidget = ComponentWidget.i18n("widgets.gui.widgets.manage.remove");
        confirmationWidget.addId("remove-confirmation");
        this.inputWidget.addContent(confirmationWidget);
        ListWidget previewWidget = new ListWidget(textReplacementWidget.getProperties());
        previewWidget.addId("remove-preview");
        this.inputWidget.addContent(previewWidget);
        HorizontalListWidget menu = new HorizontalListWidget();
        menu.addId("remove-button-menu");
        menu.addEntry(ButtonWidget.i18n("labymod.ui.button.remove", () -> {
            this.addon.configuration().getWidgets().remove(textReplacementWidget.getProperties().getID());
            this.widgets.remove(textReplacementWidget);
            this.textReplacementList.session().setSelectedEntry(null);
            this.setAction(null);
        }));
        menu.addEntry(ButtonWidget.i18n("labymod.ui.button.cancel", () -> this.setAction(null)));
        this.inputWidget.addContent(menu);
        return this.inputWidget;
    }

    private DivWidget initializeManageContainer(ListWidget textReplacementWidget) {
        ButtonWidget doneButton = ButtonWidget.i18n("labymod.ui.button.done");
        TextFieldWidget valueTextField = new TextFieldWidget();
        DivWidget inputContainer = new DivWidget();
        inputContainer.addId("input-container");
        this.inputWidget = new FlexibleContentWidget();
        this.inputWidget.addId("input-list");
        ComponentWidget labelText = ComponentWidget.i18n("widgets.gui.widgets.manage.label");
        labelText.addId("label-text");
        this.inputWidget.addContent(labelText);
        TextFieldWidget labelTextField = new TextFieldWidget();
        labelTextField.setText(textReplacementWidget.getProperties().getLabel());
        labelTextField.updateListener((newValue) ->
            doneButton.setEnabled(!labelTextField.getText().trim().isEmpty() && !valueTextField.getText().trim().isEmpty())
        );
        this.inputWidget.addContent(labelTextField);
        ComponentWidget valueText = ComponentWidget.i18n("widgets.gui.widgets.manage.value");
        valueText.addId("label-text");
        this.inputWidget.addContent(valueText);
        valueTextField.setText(textReplacementWidget.getProperties().getValue());
        valueTextField.updateListener((newValue) ->
            doneButton.setEnabled(!labelTextField.getText().trim().isEmpty() && !valueTextField.getText().trim().isEmpty())
        );
        this.inputWidget.addContent(valueTextField);
        HorizontalListWidget buttonList = new HorizontalListWidget();
        buttonList.addId("edit-button-menu");
        doneButton.setEnabled(!labelTextField.getText().trim().isEmpty() && !valueTextField.getText().trim().isEmpty());
        doneButton.setPressable(() -> {
            this.addon.configuration().getWidgets().remove(textReplacementWidget.getProperties().getID());
            GuiWidgetProperties customWidget = textReplacementWidget.getProperties();
            customWidget.setLabel(labelTextField.getText());
            customWidget.setValue(valueTextField.getText());
            this.addon.configuration().getWidgets().put(customWidget.getID(), customWidget);
            this.widgets.remove(textReplacementWidget);
            this.widgets.add(new ListWidget(customWidget));
            this.setAction(null);
        });
        buttonList.addEntry(doneButton);
        buttonList.addEntry(ButtonWidget.i18n("labymod.ui.button.cancel", () -> this.setAction(null)));
        inputContainer.addChild(this.inputWidget);
        this.inputWidget.addContent(buttonList);
        return inputContainer;
    }

    private void setAction(Action action) {
        this.action = action;
        this.reload();
    }

    public <T extends LabyScreen> @Nullable T renew() {
        return null;
    }

    private enum Action {
        ADD,
        EDIT,
        REMOVE
    }
}