package eu.first.sentify.companypage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.vaadin.ui.Label;


public class WidgetLoaderFactory extends com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory {

	/*
	 * This is the attempt for creating custom widgetset, which includes only a minimal subset
	 * of widgets necessary for the application to work properly.
	 * Currently the only way is to extend ConnectorBundleLoaderFactory and
	 * update getConnectorsForWidgetset which returns a list of connectors (widgets)
	 * to be compiled.
	 * 
	 * (non-Javadoc)
	 * @see com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory#getConnectorsForWidgetset(com.google.gwt.core.ext.TreeLogger, com.google.gwt.core.ext.typeinfo.TypeOracle)
	 */
	protected Collection<JClassType> getConnectorsForWidgetset( 
		 	TreeLogger logger, TypeOracle typeOracle) 
		 	throws UnableToCompleteException {
		
		ArrayList<String> filterOutList = new ArrayList<String>(
				Arrays.asList("com.vaadin.client.ui.passwordfield.PasswordFieldConnector",
						"com.vaadin.client.ui.draganddropwrapper.DragAndDropWrapperConnector",
						"com.vaadin.client.extensions.BrowserWindowOpenerConnector",
						"com.vaadin.client.ui.richtextarea.RichTextAreaConnector",
						"com.vaadin.client.ui.tabsheet.TabsheetConnector",
						"com.vaadin.client.ui.combobox.ComboBoxConnector",
						"com.vaadin.client.ui.checkbox.CheckBoxConnector",
						"com.vaadin.client.ui.splitpanel.VerticalSplitPanelConnector",
						"com.vaadin.client.ui.optiongroup.OptionGroupConnector",
						"com.vaadin.client.ui.splitpanel.HorizontalSplitPanelConnector",
						"com.vaadin.client.ui.browserframe.BrowserFrameConnector",
						"com.vaadin.client.ui.tree.TreeConnector",
						"com.vaadin.client.ui.colorpicker.ColorPickerGradientConnector",
						"com.vaadin.client.ui.datefield.PopupDateFieldConnector",
						"com.vaadin.client.ui.flash.FlashConnector",
						"com.vaadin.client.ui.colorpicker.ColorPickerAreaConnector",
						"com.vaadin.client.ui.colorpicker.ColorPickerConnector",
						"com.vaadin.client.ui.video.VideoConnector",
						"com.vaadin.client.ui.audio.AudioConnector",
						"com.vaadin.client.extensions.FileDownloaderConnector",
						"com.vaadin.client.ui.twincolselect.TwinColSelectConnector",
						"com.vaadin.client.ui.upload.UploadConnector",
						"com.vaadin.client.ui.colorpicker.ColorPickerGridConnector",
						"com.vaadin.client.ui.progressindicator.ProgressIndicatorConnector",
						"com.vaadin.client.ui.accordion.AccordionConnector",
						"com.vaadin.client.ui.menubar.MenuBarConnector",
						"com.vaadin.client.ui.treetable.TreeTableConnector",
						"com.vaadin.client.ui.embedded.EmbeddedConnector",
						"com.vaadin.client.ui.table.TableConnector",
						"com.vaadin.client.ui.listselect.ListSelectConnector",
						"com.vaadin.client.ui.form.FormConnector",
						"com.vaadin.client.extensions.javascriptmanager.JavaScriptManagerConnector",
						"com.vaadin.client.ui.formlayout.FormLayoutConnector",
						"com.vaadin.client.ui.popupview.PopupViewConnector"
						
						

				));		
		Collection<JClassType> c = super.getConnectorsForWidgetset(logger, typeOracle);
		
		c = filterOutTypes(c, filterOutList);
		System.out.println(c);
		
		return c;
		
	}
	
	
	private Collection<JClassType> filterOutTypes(Collection<JClassType> all, ArrayList<String> removeClasses) {
		ArrayList<JClassType> list = new ArrayList<JClassType>();
		
		for (JClassType jct : all) {
			boolean exists = false;
			for (String f: removeClasses) {		
				if (jct.toString().toLowerCase().contains(f.toLowerCase()) == true) {
					exists = true;
				}
			}
			if (exists == false) {
				list.add(jct);
			}
		}
		
		return list;
	}
}
