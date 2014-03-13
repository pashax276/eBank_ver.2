package ui.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.Serializable;

/**
 * Created by Павел on 13.03.14.
 */
public class UIUtils implements Serializable{

    private int viewLoadCount = 0;

    public void greetOnViewLoad(ComponentSystemEvent event){
        FacesContext context = FacesContext.getCurrentInstance();
        if (viewLoadCount < 1 && !context.isPostback()){
            String firstName = (String) event.getComponent().getAttributes().get("firstName");
            String lastName = (String) event.getComponent().getAttributes().get("lastName");

            FacesMessage message = new FacesMessage(String.format("Welcome to your account %s %s", firstName, lastName));
            context.addMessage("growlMessages", message);
            viewLoadCount++;
        }
    }
}
