package eu.first.sentify.widget.drilldown;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.SentimentObject;
import eu.first.commons.ws.client.SentimentServiceStub.TrendingTopic;
import eu.first.sentify.configuration.Functions;
import eu.first.sentify.searchsentimentobjects.CompanySearch;

public class DDownListNuevo extends CustomComponent {

	CustomLayout custom = null;
	long docId = 0;
	CustomLayout custom2 = null;
	Button sentOb = null;
	RowList rl = null;
	Functions f = new Functions();

	CompanySearch cs = CompanySearch.getInstance();

	// Logger logger = Logger.getLogger(DDownListNuevo.getCanonicalName());

	public DDownListNuevo(long docId, CustomLayout custom2) {

		this.docId = docId;

		// Create table template
		custom = new CustomLayout("drilldowntable");
		custom.setSizeFull();

		// Paint the document list table
		getPaintData();

		// The composition root MUST be set
		setCompositionRoot(custom);
	}

	private void getPaintData() {
		SentimentObject so = null;

		VerticalLayout vo = new VerticalLayout();
		vo.setMargin(false);

		custom.addComponent(vo, "sentimentObjects");

		try {
			SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());

			SentimentServiceStub.GetSentimentObjectsbyDocument req = new SentimentServiceStub.GetSentimentObjectsbyDocument();
			req.setDocument_id(docId);

			System.out.println("ALP=>Inicio valores para GetSentimentObjectsbyDocument");

			SentimentServiceStub.GetSentimentObjectsbyDocumentResponse response = wp5
					.getSentimentObjectsbyDocument(req);
			TrendingTopic[] s = response.get_return();

			System.out.println("ALP=> Obtengo " + s.length + "so's");
			if ((s != null) && (s.length > 0)) {
				for (int i = 0; i < s.length; i++) {

					SentimentServiceStub.TrendingTopic sent = s[i];

					so = sent.getTopic();
					String soName = (String) so.getName();
					String so_URI = so.getOntologyConceptUri();

					ClickListener listener = new ClickListener() {
						public void buttonClick(ClickEvent event) {

							// TODO fix progress indicator
							ProgressIndicator pi = new ProgressIndicator();
							pi.setIndeterminate(true);
							pi.setPollingInterval(5000);
							pi.setEnabled(false);

							custom.addComponent(pi, "phraseList");

							String URI_id = (String) event.getButton().getData();
							String URI = URI_id.substring(0, URI_id.indexOf("_#_"));
							long selId = Long
									.parseLong(URI_id.substring(URI_id.indexOf("_#_") + 3));

							System.out.println("ALP=> Creo PhraseList con URI=" + URI + " e ID = "
									+ selId);
							PhraseList phL = new PhraseList(URI, selId);

							custom.addComponent(phL, "phraseList");
						}
					};

					String nameResponse = cs.getCompanyNameByURIFragment(soName);
					if (nameResponse != null) {
						soName = nameResponse;
					}

					sentOb = new Button(soName, listener);
					sentOb.setData(so_URI + "_#_" + docId);
					sentOb.setStyleName(BaseTheme.BUTTON_LINK);

					double score = sent.getScore();

					if (score < 0) {
						rl = new RowList(sentOb, false, true);
						// rl = new SingleRow(doc,source,date,false);
					} else {
						rl = new RowList(sentOb, true,  true);
						// rl = new SingleRow(doc,source,date,true);
					}

					System.out.println("ALP=> soName=" + soName);
					System.out.println("ALP=> score=" + score);
					vo.addComponent(rl);
				}
			}

		} catch (Exception e) {

			rl = new RowList(new Label(
					"Sorry! This document doesn't contain information about sentiment objects."));
			vo.addComponent(rl);
			System.out.println("Drilldown error on document: " + docId);
			e.printStackTrace();
		}
	}

}
