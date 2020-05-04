package com.am.sms.model.db.imp;

import com.am.sms.model.data.Collaborator;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface CollaboratorImplements
{
    public void addItem( ConnectionFactory cf, Collaborator collaborators ) throws Exception;
    public void updateItem( ConnectionFactory cf, Collaborator collaborators ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Collaborator collaborators ) throws Exception;
    public Collaborator getCollaboratorLogin( ConnectionFactory cf, String login, String password ) throws Exception;
    public List<Collaborator> readCollaborators() throws Exception;
}
