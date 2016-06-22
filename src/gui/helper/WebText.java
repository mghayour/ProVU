/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Ghayour
 */
public class WebText extends Pane {
    
    String text = "";
    WebView web = null;
    WebEngine webEng = null;
    
    
    public WebText() {
        super();
        web = new WebView();
        webEng = web.getEngine();
        web.setDisable(true);
        super.getChildren().add(web);
    }
    
    
    // setter getter
    public void setText(String text) {
        this.text = text;
        webEng.loadContent(text);
    }
    public String getText() {
        return text;
    }

    
    
    
}
