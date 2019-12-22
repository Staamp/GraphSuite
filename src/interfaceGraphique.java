import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class interfaceGraphique extends Application {



    @Override
    public void start(final Stage primaryStage) throws Exception {

        final TextArea textArea1 = new TextArea("digraph g {\n \n \n}");
        textArea1.setMinSize(250, 150);
        textArea1.setMaxSize(250, 300);

        final TextArea textArea2 = new TextArea("Reponse");
        textArea2.setMinSize(250, 150);
        textArea2.setMaxSize(250, 300);

        MenuBar mb = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu optionsMenu = new Menu("Options");
        final Menu randomMenu = new Menu("Random");
        final Menu helpMenu = new Menu("Help");

        MenuItem loadFile = new MenuItem("Open Graph");
        MenuItem saveFile = new MenuItem("Save Graph");
        MenuItem PDF = new MenuItem("Export in PDF");

        MenuItem addNode = new MenuItem("Add Node");
        MenuItem addEdge = new MenuItem("Add Edge");
        MenuItem removeNode = new MenuItem("Remove Node");
        MenuItem removeEdge = new MenuItem("Remove Edge");
        MenuItem getSucessors = new MenuItem("getSucessors");
        MenuItem getInEdge = new MenuItem("getInEdge");
        MenuItem getOutEdge = new MenuItem("getOutEdge");
        MenuItem getIncidentEdge = new MenuItem("getIncidentEdge");
        MenuItem getAllNode = new MenuItem("getAllNode");
        MenuItem getAllEdge = new MenuItem("getAllEdge");
        MenuItem getSucessorArray = new MenuItem("getSucessorArray");
        MenuItem getAdjMatrix = new MenuItem("getAdjMatrix");
        MenuItem getReverseGraph = new MenuItem("getReverseGraph");
        MenuItem getTransitiveClosure = new MenuItem("getTransitiveClosure");
        MenuItem getDFS = new MenuItem("getDFS");
        MenuItem getBFS = new MenuItem("getBFS");

        MenuItem DenseGraph = new MenuItem("Dense Graph");
        MenuItem SparseGraph = new MenuItem("Sparse Graph");
        MenuItem ConnectedGraph = new MenuItem("Connected Graph");
        MenuItem ParameterizedGraph = new MenuItem("Parameterized Graph");
        MenuItem DAG = new MenuItem("Directed Acyclic Graph");

        MenuItem help = new MenuItem("Help");

        fileMenu.getItems().addAll(loadFile, saveFile, PDF);
        optionsMenu.getItems().addAll(addNode, addEdge, removeNode, removeEdge, getSucessors, getInEdge, getOutEdge, getIncidentEdge, getAllNode, getAllEdge, getSucessorArray, getAdjMatrix, getDFS, getBFS);
        randomMenu.getItems().addAll(ConnectedGraph, DenseGraph, SparseGraph, ParameterizedGraph, DAG);
        helpMenu.getItems().addAll(help);

        mb.getMenus().addAll(fileMenu, optionsMenu, randomMenu, helpMenu);

        BorderPane bp = new BorderPane();
        bp.setTop(mb);

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = "";
                text += "Welcome on GraphSoft !\n\n";
                text += "You can make graph with function in menu bar or with dot format in the left text area.\n\n";
                text += "You can load and export graph\n";
                text += " -Save your work in file menu\n";
                text += " -Load a graph (.dot) in file menu\n";
                text += " -Export graph to obtain a pdf image in file menu\n";
                text += "\nYou can create and watch a graph an realise many operations on graph\n";
                text += " -Add Remove Node and Edge in option menu\n";
                text += " -Get successors of one Node in option menu\n";
                text += " -Get in, out and incident edge of one Node option menu\n";
                text += " -Get all nodes and all edges in option menu\n";
                text += " -Get successor array and adjacency matrix in option menu\n";
                text += " -Get reverse graph and transitive closure in option menu\n";
                text += " -Get DFS and BFS starting from any node\n";
                text += "\n You can generated random graph\n";
                text += " -Connected graph in random menu\n";
                text += " -Dense graph in random menu\n";
                text += " -Sparse graph in random menu\n";
                text += " -Parameterized graph in random menu\n";
                text += " -Directed Acyclic Graph in random menu\n\n";
                text += "Developped by Nicolas Courvoisier";
                textArea2.setText(text);
            }
        });

        saveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOT files (*.dot)", "*.dot");
                fc.getExtensionFilters().add(extFilter);
                File file = fc.showSaveDialog(primaryStage);
                if (file != null) {
                    try {
                        PrintWriter writer;
                        writer = new PrintWriter(file);
                        writer.println(textArea1.getText());
                        writer.close();
                    } catch (IOException ex) {
                        // Logger.getLogger(SaveFileWithFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error");
                    }
                }
            }
        });

        final FileChooser fileChooser = new FileChooser();
        loadFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            textArea1.clear();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                Graf g = Graf.DotFileToGraph(file.toString());
                textArea1.setText(g.toDotString());
            }
            }
        });

        PDF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea1.getText();
                Graf g = Graf.StringToGraph(text);
                g.DotFileToPDFImage();
            }
        });

        getSucessors.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Get successors of one node");
                inDialog.setHeaderText("Write node for obtain the successors of one edge");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    List<Node> ls = g.getSuccessors(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    for (int i = 0; i < ls.size(); i++) {
                        txt += ls.get(i);
                        txt += "\n";
                    }
                    textArea2.setText(txt);
                }
            }
        });

        addNode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Add one node");
                inDialog.setHeaderText("Write node to add");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    g.addNode(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    textArea1.setText(g.toDotString());
                }
            }
        });

        removeNode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Remove one node");
                inDialog.setHeaderText("Write node to remove");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    g.removeNode(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    textArea1.setText(g.toDotString());
                }
            }
        });

        addEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Add new edge");
                dialog.setHeaderText("Add new edge with node from and node to.");

                ButtonType loginButtonType = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField nodeFrom = new TextField();
                nodeFrom.setPromptText("Node from");
                TextField nodeTo = new TextField();
                nodeTo.setPromptText("Node to");

                grid.add(new Label("Node from:"), 0, 0);
                grid.add(nodeFrom, 1, 0);
                grid.add(new Label("Node to:"), 0, 1);
                grid.add(nodeTo, 1, 1);

                javafx.scene.Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
                loginButton.setDisable(true);
                nodeFrom.textProperty().addListener((observable, oldValue, newValue) -> {
                    loginButton.setDisable(newValue.trim().isEmpty());
                });

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == loginButtonType) {
                        return new Pair<>(nodeFrom.getText(), nodeTo.getText());
                    }
                    return null;
                });

                Optional<Pair<String, String>> result = dialog.showAndWait();

                if (result.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    g.addEdge(new Node(Integer.parseInt(result.get().getKey())), new Node(Integer.parseInt(result.get().getValue())));
                    textArea1.setText(g.toDotString());
                }
            }
        });

        removeEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Remove new edge");
                dialog.setHeaderText("Remove new edge with node from and node to.");

                ButtonType loginButtonType = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField nodeFrom = new TextField();
                nodeFrom.setPromptText("Node from");
                TextField nodeTo = new TextField();
                nodeTo.setPromptText("Node to");

                grid.add(new Label("Node from:"), 0, 0);
                grid.add(nodeFrom, 1, 0);
                grid.add(new Label("Node to:"), 0, 1);
                grid.add(nodeTo, 1, 1);

                javafx.scene.Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
                loginButton.setDisable(true);
                nodeFrom.textProperty().addListener((observable, oldValue, newValue) -> {
                    loginButton.setDisable(newValue.trim().isEmpty());
                });

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == loginButtonType) {
                        return new Pair<>(nodeFrom.getText(), nodeTo.getText());
                    }
                    return null;
                });

                Optional<Pair<String, String>> result = dialog.showAndWait();

                if (result.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    g.removeEdge(new Node(Integer.parseInt(result.get().getKey())), new Node(Integer.parseInt(result.get().getValue())));
                    textArea1.setText(g.toDotString());
                }
            }
        });

        getOutEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Get out node");
                inDialog.setHeaderText("Write node for obtain the out edge");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    List<Edge> ls = g.getOutEdge(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    for (int i = 0; i < ls.size(); i++) {
                        txt += ls.get(i);
                        txt += "\n";
                    }
                    textArea2.setText(txt);
                }
            }
        });

        getInEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                 inDialog.setTitle("Get in node");
                 inDialog.setHeaderText("Write node for obtain the in edge");
                 inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    List<Edge> ls = g.getInEdge(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    for (int i = 0; i < ls.size(); i++) {
                        txt += ls.get(i);
                        txt += "\n";
                    }
                    textArea2.setText(txt);
                }
            }
        });

        getIncidentEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Get incident node");
                inDialog.setHeaderText("Write node for obtain the incident edge");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    List<Edge> ls = g.getIncidentEdges(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    for (int i = 0; i < ls.size(); i++) {
                        txt += ls.get(i);
                        txt += "\n";
                    }
                    textArea2.setText(txt);
                }
            }
        });

        getAllNode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea1.getText();
                Graf g = Graf.StringToGraph(text);
                List<Node> ls = g.getAllNodes();
                String txt = "";
                for (int i = 0; i < ls.size(); i++) {
                    txt += ls.get(i);
                    txt += "\n";
                }
                textArea2.setText(txt);
            }
        });

        getAllEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea1.getText();
                Graf g = Graf.StringToGraph(text);
                List<Edge> ls = g.getAllEdges();
                String txt = "";
                for (int i = 0; i < ls.size(); i++) {
                    txt += ls.get(i);
                    txt += "\n";
                }
                textArea2.setText(txt);
            }
        });

        getSucessorArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea1.getText();
                Graf g = Graf.StringToGraph(text);
                int[] sa = g.getSuccessorArray();
                String txt = "";
                for (int i = 0; i < sa.length; i++) {
                    txt += sa[i];
                    txt += "\n";
                }
                textArea2.setText(txt);
            }
        });

        getAdjMatrix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea1.getText();
                Graf g = Graf.StringToGraph(text);
                int[][] adjMatrix = g.getAdjMatrix();
                int lgtTabSA = adjMatrix.length;
                String txt = "#Adjency Matrix : \n";
                for (int i = 0; i < lgtTabSA; i++) {
                    for (int j = 0; j < lgtTabSA; j++) {
                        txt += adjMatrix[i][j] + " ";
                    }
                    txt += "\n";
                }
                textArea2.setText(txt);
            }
        });

        getReverseGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            String text = textArea1.getText();
            Graf g = Graf.StringToGraph(text);
            Graf rg = g.getReverseGraph();
            textArea2.setText("#REVERSE GRAPH \n\n" + rg.toDotString());
            }
        });

        getTransitiveClosure.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea1.getText();
                Graf g = Graf.StringToGraph(text);
                Graf rg = g.getTransitiveClosure();
                textArea2.setText("#TRANSITIVE CLOSURE \n\n" + rg.toDotString());
            }
        });

        getDFS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Get DFS of graph");
                inDialog.setHeaderText("Write node for obtain the node to start DFS");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    List<Node> ls = g.getDFS(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    for (int i = 0; i < ls.size(); i++) {
                        txt += ls.get(i);
                        txt += "\n";
                    }
                    textArea2.setText("#DFS\n" + txt);
                }
            }
        });

        getBFS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog inDialog = new TextInputDialog("1");
                inDialog.setTitle("Get BFS of graph");
                inDialog.setHeaderText("Write node for obtain the node to start BFS");
                inDialog.setContentText("Node :");
                Optional<String> textIn = inDialog.showAndWait();
                if (textIn.isPresent()) {
                    String text = textArea1.getText();
                    Graf g = Graf.StringToGraph(text);
                    List<Node> ls = g.getBFS(new Node(Integer.parseInt(textIn.get())));
                    String txt = "";
                    for (int i = 0; i < ls.size(); i++) {
                        txt += ls.get(i);
                        txt += "\n";
                    }
                    textArea2.setText("#BFS" + txt);
                }
            }
        });


        ConnectedGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Graf g = Graf.connectedGraph();
                String txt = g.toDotString();
                textArea1.setText(txt);
            }
        });

        DenseGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Graf g = Graf.denseGraph();
                String txt = g.toDotString();
                textArea1.setText(txt);
            }
        });

        SparseGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Graf g = Graf.sparseGraph();
                String txt = g.toDotString();
                textArea1.setText(txt);
            }
        });

        ParameterizedGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Graf g = Graf.parameterizedGraph(5, 2, 0);
                String txt = g.toDotString();
                textArea1.setText(txt);
            }
        });

        DAG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Graf g = Graf.DAG();
                String txt = g.toDotString();
                textArea1.setText(txt);
            }
        });




        bp.setLeft(textArea1);
        bp.setRight(textArea2);

        primaryStage.setTitle("GraphSoft");
        Scene scene = new Scene(bp, 700, 400);

        BorderPane.setMargin(bp, new Insets(20, 20, 20, 20));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
