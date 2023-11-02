//public void initialize(URL url, ResourceBundle resourceBundle) {
//        mail.setCellValueFactory(new PropertyValueFactory<ProfData, String>("mail"));
//        ObjectMapper mapper = new ObjectMapper();
//        table.setEditable(true);
//        try {
//        Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>(){});
//        List<Map<String, String>> profsMap = usersMap.get("profs");
//        List<Map<String, String>> MatieresMap = usersMap.get("Matieres");
//        ObservableList<ProfData> notes = FXCollections.observableArrayList();
//        ArrayList<TableColumn<ProfData, ?>> MatiereColumns = new ArrayList<>();
//
//        for (Map<String, String> Matiere : MatieresMap) {
//        // Obtenez la liste des colonnes existantes
//        ObservableList<TableColumn<ProfData, ?>> existingColumns = table.getColumns();
//
//        // Vérifiez si la colonne existe déjà
//        boolean columnExists = false;
//        for (TableColumn<ProfData, ?> column : existingColumns) {
//        if (column.getText().equals(Matiere.get("Matiere"))) {
//        columnExists = true;
//        break;
//        }
//        }
//
//        if (!columnExists) {
//        TableColumn<ProfData, String> newMatiereColumn = new TableColumn<>(Matiere.get("Matiere"));
//        newMatiereColumn.setPrefWidth(217.5999755859375);
//
//
//        table.setEditable(true);
//        newMatiereColumn.setCellValueFactory(cellData -> {
//        ProfData note = cellData.getValue();
//        Map<String, MatiereData> Matieres = note.getMatieres();
//        MatiereData MatiereData = Matieres.get(Matiere.get("Matiere"));
//        return new SimpleObjectProperty<>(MatiereData);
//        });
//
//        newMatiereColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
//        newMatiereColumn.setOnEditCommit(event -> {
//        ProfData note = event.getRowValue();
//        Map<String, MatiereData> Matieres = note.getMatieres();
//        enregistrer();
//        });
//        }
//        for (Map<String, String> prof : profsMap) {
//        ProfData newprof = new ProfData(prof.get("mail"));
//        for (Map<String, String> Matiere : MatieresMap) {
//        MatiereData newMatiere = Matiere.get("Matiere") ;
//        newprof.addMatiere(Matiere.get("Matiere"), newMatiere);
//        }
//        notes.add(newprof);
//        }
//
//        table.setItems(notes);
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
//        }