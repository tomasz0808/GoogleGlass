package com.projektgrupowy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.googleglass.R;
import com.example.googleglass.R.id;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

public class MainActivity extends Activity {

	private List<CardBuilder> mCards;
	private CardScrollView mCardScrollView;
	private ExampleCardScrollAdapter mAdapter;
	private GetFonts getFonts;
	

	TextView titleText;
	TextView bodyText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// android.os.Debug.waitForDebugger(); //uncomment if you want to debbug in eclipse

		getFonts = new GetFonts(getApplicationContext());

		createCards();
		mCardScrollView = new CardScrollView(this);
		mAdapter = new ExampleCardScrollAdapter();
		mCardScrollView.setAdapter(mAdapter);
		mCardScrollView.activate();
		setupClickListener();

		setContentView(mCardScrollView);

	}

	private void createCards() {
		mCards = new ArrayList<CardBuilder>();

		mCards.add(new CardBuilder(this, CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.activity_main));
		mCards.add(new CardBuilder(this, CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.activity_main));
		mCards.add(new CardBuilder(this, CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.activity_main));
		mCards.add(new CardBuilder(this, CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.activity_main));

	}

	private class ExampleCardScrollAdapter extends CardScrollAdapter {

		@Override
		public int getPosition(Object item) {
			return mCards.indexOf(item);
		}

		@Override
		public int getCount() {
			return mCards.size();
		}

		@Override
		public Object getItem(int position) {
			return mCards.get(position);
		}

		@Override
		public int getViewTypeCount() {
			return CardBuilder.getViewTypeCount();
		}

		@Override
		public int getItemViewType(int position) {
			return mCards.get(position).getItemViewType();
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = new CardBuilder(getApplicationContext(), CardBuilder.Layout.EMBED_INSIDE).setEmbeddedLayout(R.layout.activity_main).setFootnote("Footnote" + position)
					.getView();

			String currentHeaderText;
			String currentFontName;
			Typeface currentFont;
			int currentSize=0;
			
			titleText = (TextView)view.findViewById(id.headTextView);
			bodyText = (TextView) view.findViewById(id.bodyTextView);
			
			switch (position) {
			case 0:{
				currentSize = 12;
				currentFontName = getFonts.getFontNameByID(position);
				currentFont = getFonts.getFontByID(position);	
				break;
			} case 1:{
				currentSize = 12;
				currentFontName = getFonts.getFontNameByID(position);
				currentFont = getFonts.getFontByID(position);	
				break;
			} case 2:{
				currentSize = 12;
				currentFontName = getFonts.getFontNameByID(position);
				currentFont = getFonts.getFontByID(position);	
				break;
			} case 3:{
				currentSize = 12;
				currentFontName = getFonts.getFontNameByID(position);
				currentFont = getFonts.getFontByID(position);	
				break;
			}
			default:
				currentSize = 12;
				currentFontName = getFonts.getFontNameByID(0);
				currentFont = getFonts.getFontByID(0);	
				break;
			}
			
			
			currentHeaderText = "Czcionka "+currentFontName+", rozmiar "+currentSize;			
			
			titleText.setText(currentHeaderText);
			titleText.setTypeface(currentFont);
			titleText.setTextColor(Color.WHITE);
			titleText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, currentSize);
			
			return view;
		}
	}

	private void setupClickListener() {
		mCardScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				am.playSoundEffect(Sounds.TAP);
			}
		});
	}
}