import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class NovoProjetoGUI extends JFrame {
    private JLabel labelNome, labelAcronimo, labelDuracao, labelDataI, labelDataF, lblChefe;
    private JTextField txtNome, txtAcronimo, txtDuracao, txtDataI, txtDataF;
    protected JButton btnCria, btnVoltar;
    protected JComboBox comboPessoa;
    protected DefaultComboBoxModel modelPessoa;
    protected String[] aux;
    private ArrayList<Pessoa> peepz = new ArrayList<>();
    ArrayList<Projeto> projetos = new ArrayList<>();
    Projeto proj;
    MainGUI menu;
    public NovoProjetoGUI(MainGUI menu) {
        this.menu=menu;
        setLayout(null);
        labelNome = new JLabel("Nome do projeto: ");
        labelNome.setBounds(40, 40, 200, 20);
        labelAcronimo = new JLabel("Acrónimo: ");
        labelAcronimo.setBounds(40, 70, 150, 20);
        labelDuracao = new JLabel("Duração do projeto:");
        labelDuracao.setBounds(40, 100, 500, 20);
        txtNome = new JTextField();
        txtNome.setBounds(160, 40, 233, 20);
        txtAcronimo = new JTextField();
        txtAcronimo.setBounds(120, 70, 100, 20);
        txtDuracao = new JTextField("em meses");
        txtDuracao.setBounds(160, 100, 100, 20);
        labelDataI = new JLabel("Data de inicio:");
        labelDataI.setBounds(40, 130, 225, 20);
        labelDataF = new JLabel("Data de fim:");
        labelDataF.setBounds(240, 130, 225, 20);
        txtDataI = new JTextField("dd/mm/yyyy");
        txtDataI.setBounds(140, 130, 80, 20);
        txtDataF = new JTextField("dd/mm/yy");
        txtDataF.setBounds(330, 130, 80, 20);
        btnCria = new JButton("Criar");
        btnCria.setBounds(120, 200, 80, 20);
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(220, 200, 80, 20);
        add(btnVoltar);
        add(labelAcronimo);
        add(labelDataF);
        add(labelDataI);
        add(labelNome);
        add(labelNome);
        add(labelDuracao);
        add(txtAcronimo);
        add(txtDuracao);
        add(txtNome);
        add(txtDataI);
        add(txtDataF);
        add(btnCria);
        for(Pessoa pessoa:menu.cisuc.listaPessoas){
            if(pessoa.getTipo()==0){
                peepz.add(pessoa);
            }
        }
        aux = new String[peepz.size()];
        for(int i=0; i<peepz.size(); i++){
            aux[i]=peepz.get(i).getUser();
        }
        modelPessoa = new DefaultComboBoxModel(aux);
        comboPessoa = new JComboBox(modelPessoa);
        comboPessoa.setBounds(260, 160, 80, 20);
        lblChefe = new JLabel("Selecione um investigador principal");
        lblChefe.setBounds(40, 160, 170, 20);
        add(lblChefe);
        add(comboPessoa);
        if(peepz.size()>0){
            comboPessoa.setSelectedIndex(0);
        }
        btnCria.addActionListener(e->{
            if (txtDuracao.getText()==null || txtAcronimo.getText()==null || txtDataF.getText()==null || txtDataI.getText()==null || txtNome.getText()==null){
                JOptionPane.showMessageDialog(null, "Preencha os campos todos");
            }
            String[] data = txtDataI.getText().split("/");
            Data dataI = new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            data = txtDataF.getText().split("/");
            Data dataF = new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            if(dataI.checkData() && dataF.checkData()){
                proj = new Projeto(txtNome.getText(), txtAcronimo.getText(), dataI, dataF, Integer.parseInt(txtDuracao.getText()), peepz.get(comboPessoa.getSelectedIndex()));
                menu.cisuc.listaProjeto.add(proj);
                Ficheiro ficheiro = new Ficheiro();
                ficheiro.WriteToFile(menu.cisuc);
                JOptionPane.showMessageDialog(null,"Projeto criado com sucesso.");
            }else{
                JOptionPane.showMessageDialog(null, "Data Invalida");
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltarActionListener();
            }
        });
        txtDuracao.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                txtDuracao.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        txtDataI.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                txtDataI.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        txtDataF.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                txtDataF.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
    }
    private void btnVoltarActionListener(){
        MainGUI menu = new MainGUI(this.menu.cisuc);
        menu.setSize(420, 300);
        menu.setVisible(true);
        this.setVisible(false);
    }
}
