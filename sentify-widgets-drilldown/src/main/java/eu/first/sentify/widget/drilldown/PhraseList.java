package eu.first.sentify.widget.drilldown;

import java.util.logging.Logger;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;

import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentPhrasebyDocumentResponse;
import eu.first.commons.ws.client.SentimentServiceStub.Phrase;
import eu.first.sentify.configuration.Functions;
import eu.first.sentify.helpers.TextEncodingFix;

public class PhraseList extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372105390969023321L;
	CustomLayout custom = null;
	String URI = "";
	long docId = 0;
	Button text = null;
	RowList rl = null;
	Functions f = new Functions();
	Logger logger = Logger.getLogger(PhraseList.class.getCanonicalName());

	public PhraseList(String URI, long docId) {

		this.URI = URI;
		this.docId = docId;

		setMargin(false);

		// Paint the document list table
		getPaintData();

	}

	private void getPaintData() {
		String phraseText = null;

		try {
			SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());

			SentimentServiceStub.GetSentimentPhrasebyDocument req = new SentimentServiceStub.GetSentimentPhrasebyDocument();
			req.setDocument_id(docId);
			req.setSentiment_object_uri(URI);

			System.out.println("ALP=>Inicio valores para getSentimentPhrasebyDocument.docId="
					+ docId + " URI=" + URI);

			GetSentimentPhrasebyDocumentResponse response = wp5.getSentimentPhrasebyDocument(req);
			Phrase[] s = response.get_return();

			if ((s != null) && (s.length > 0)) {
				for (int i = 0; i < s.length; i++) {

					Phrase sent = s[i];

					phraseText = TextEncodingFix.fixText(sent.getText());
					text = new Button();
					text.setCaption(phraseText);
					text.setStyleName("mylink");
					text.setEnabled(false);

					double score = sent.getSentimentScore();

					if (score < 0) {
						// rl = new SingleRow(doc,source,date,false);
						rl = new RowList(text, false, true);
					} else {
						rl = new RowList(text, true, true);
					}

					System.out.println("ALP=> text=" + phraseText);
					System.out.println("ALP=> score=" + score);

					addComponent(rl);
				}

			} else {
				System.out.println("ALP=> No tiene phrases");
				// When there's no phrases => actually this should never happen
				Button b = new Button(
						"Strange! There's no phrases related to this sentiment object");
				b.setEnabled(false);
				rl = new RowList(b, true, false);

				addComponent(rl);
			}

		} catch (Exception e) {
			// When there's no phrases => actually this should never happen
			Button b = new Button("Error retrieving data. Please try again in few seconds.");
			b.setEnabled(false);
			rl = new RowList(b, true, false);

			addComponent(rl);

			System.out.println("Drill-down error in PhraseList");
			e.printStackTrace();
		}
	}

}
