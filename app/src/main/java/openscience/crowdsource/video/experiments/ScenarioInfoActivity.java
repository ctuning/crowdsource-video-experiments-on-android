package openscience.crowdsource.video.experiments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

import static openscience.crowdsource.video.experiments.MainActivity.setTaskBarColored;
import static openscience.crowdsource.video.experiments.RecognitionScenarioService.getScenarioDescriptionHTML;

/**
 * Screen displays selected scenario information
 *
 * @author Daniil Efremov
 */
public class ScenarioInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_info);

        setTaskBarColored(this);

        View selectScenario = (View)findViewById(R.id.topSelectedScenario);
        selectScenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ScenarioInfoActivity.this, ScenariosActivity.class);
                startActivity(homeIntent);
            }
        });
        TextView selectScenarioText = (TextView)findViewById(R.id.topSelectedScenarioText);
        String selectedScenarioTitle = getIntent().getStringExtra(ScenariosActivity.SELECTED_SCENARIO_TITLE);
        selectScenarioText.setText(selectedScenarioTitle);

        RecognitionScenario selectedScenario = null;
        ArrayList<RecognitionScenario> sortedRecognitionScenarios = RecognitionScenarioService.getSortedRecognitionScenarios();
        for (RecognitionScenario sortedRecognitionScenario : sortedRecognitionScenarios) {
            if (sortedRecognitionScenario.getTitle().equalsIgnoreCase(selectedScenarioTitle)) {
                selectedScenario = sortedRecognitionScenario;
            }
        }

        TextView scenarioInfoText = (TextView)findViewById(R.id.scenarioInfoText);
        try {
            scenarioInfoText.setText(Html.fromHtml(getScenarioDescriptionHTML(selectedScenario)));
        } catch (JSONException e) {
            AppLogger.logMessage("Error " + e.getLocalizedMessage());
        }

        Button homeButton = (Button) findViewById(R.id.btn_home_recognizeMain);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ScenarioInfoActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });


        Button consoleButton = (Button) findViewById(R.id.btn_consoleMain);
        consoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent(ScenarioInfoActivity.this, ConsoleActivity.class);
                startActivity(logIntent);
            }
        });


        Button infoButton = (Button) findViewById(R.id.btn_infoMain);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(ScenarioInfoActivity.this, InfoActivity.class);
                startActivity(infoIntent);
            }
        });

    }
}
