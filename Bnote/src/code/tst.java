//table.setEditable(true); // Ajoutez cette ligne pour rendre le TableView éditable
//
//        note.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        note.setOnEditCommit(
//        new EventHandler<CellEditEvent<noteData, Integer>>() {
//@Override
//public void handle(CellEditEvent<noteData, Integer> t) {
//        ((noteData) t.getTableView().getItems().get(
//        t.getTablePosition().getRow())
//        ).setNote(t.getNewValue());
//        enregistrer();
//        }
//        }
//        );
//        controle.setCellFactory(TextFieldTableCell.forTableColumn());
//        controle.setOnEditCommit(
//        new EventHandler<CellEditEvent<noteData, String>>() {
//@Override
//public void handle(CellEditEvent<noteData, String> t) {
//        ((noteData) t.getTableView().getItems().get(
//        t.getTablePosition().getRow())
//        ).setControle(t.getNewValue());
//        enregistrer();
//        }
//        }
//        );
//        coef.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        coef.setOnEditCommit(
//        new EventHandler<CellEditEvent<noteData, Integer>>() {
//@Override
//public void handle(CellEditEvent<noteData, Integer> t) {
//        ((noteData) t.getTableView().getItems().get(
//        t.getTablePosition().getRow())
//        ).setCoef(t.getNewValue());
//        enregistrer();
//        }
//        }
//        );
//        appreciation.setCellFactory(TextFieldTableCell.forTableColumn());
//        appreciation.setOnEditCommit(
//        new EventHandler<CellEditEvent<noteData, String>>() {
//@Override
//public void handle(CellEditEvent<noteData, String> t) {
//        ((noteData) t.getTableView().getItems().get(
//        t.getTablePosition().getRow())
//        ).setAppreciation(t.getNewValue());
//        enregistrer();
//        }
//        }
//        );