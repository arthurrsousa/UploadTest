package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controle.*;

/**
 * Tela de listagem das instancias das classes do software
 * @author Arthur Rodrigues Sousa
 *
 */
public class TelaLista implements ActionListener, ListSelectionListener {		
	private JFrame janela;
	private JLabel titulo;
	private JButton cadastroProd;
	private JButton refreshProd;
	private JButton cadastroCliente;
	private JButton refreshCliente;
	//private JButton cadastroRecibo;
	private JButton refreshRecibo;
	private static ControleDados dados;
	//private JTextField VendedorCadastrado;
	private JList<String> listaProdsCadastrados;
	private JList<String> listaClientesCadastrados;
	private JList<String> listaRecibosCadastrados;
	//private String nomeVend;
	private String[] listaNomes = new String[50];
	private int cliIndex;

	/**
	 * Altera os dados que serao apresentados dependendo do botao (opcao) selecionado.
	 * 
	 * @param d - dados que estao sendo usados no sistema
	 * @param op - opcao selecionada
	 */
	public void mostrarDados(ControleDados d, int op){
		dados = d;

		switch (op) {
		case 1:// Mostrar detalhes do Vendedor
			
			new TelaDetalhes().inserirEditar(1, dados, 0);
			break;

		case 2:// Mostrar dados de Produtos cadastrados (JList)
			listaNomes = new ControleProduto(dados).getNomeProd();
			listaProdsCadastrados = new JList<String>(listaNomes);
			janela = new JFrame("Produtos");
			titulo = new JLabel("Produtos Cadastrados");
			cadastroProd = new JButton("Cadastrar");
			refreshProd = new JButton("Refresh");

			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(90, 10, 250, 30);
			listaProdsCadastrados.setBounds(20, 50, 350, 120);
			listaProdsCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaProdsCadastrados.setVisibleRowCount(10);


			cadastroProd.setBounds(70, 177, 100, 30);
			refreshProd.setBounds(200, 177, 100, 30);

			janela.setLayout(null);

			janela.add(titulo);
			janela.add(listaProdsCadastrados);
			janela.add(cadastroProd);
			janela.add(refreshProd);

			janela.setSize(400, 250);
			janela.setVisible(true);

			cadastroProd.addActionListener(this);
			refreshProd.addActionListener(this);
			listaProdsCadastrados.addListSelectionListener(this);
			break;
			
		case 3:// Mostrar dados de clientes cadastrados (JList)
			listaNomes = new ControleCliente(dados).getNomeCliente();
			listaClientesCadastrados = new JList<String>(listaNomes);
			janela = new JFrame("Clientes");
			titulo = new JLabel("Clientes Cadastrados");
			cadastroCliente = new JButton("Cadastrar");
			refreshCliente = new JButton("Refresh");

			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(90, 10, 250, 30);
			listaClientesCadastrados.setBounds(20, 50, 350, 120);
			listaClientesCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaClientesCadastrados.setVisibleRowCount(10);


			cadastroCliente.setBounds(70, 177, 100, 30);
			refreshCliente.setBounds(200, 177, 100, 30);

			janela.setLayout(null);

			janela.add(titulo);
			janela.add(listaClientesCadastrados);
			janela.add(cadastroCliente);
			janela.add(refreshCliente);

			janela.setSize(400, 250);
			janela.setVisible(true);

			cadastroCliente.addActionListener(this);
			refreshCliente.addActionListener(this);
			listaClientesCadastrados.addListSelectionListener(this);
			break;
			
			
		default:
			JOptionPane.showMessageDialog(null,"Op��o n�o encontrada!", null, 
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	/**
	 * Sobrecarga que acrecenta o index do cliente portador dos recibos a serem apresentados
	 * @param index - posicao do Cliente no array
	 */
	public void mostrarDados(ControleDados d, int op, int index){
		cliIndex = index;
		dados = d;
		if (op == 4) {// Mostrar dados de Recibos cadastrados (JList)
			listaNomes = new ControleRecibo(dados, cliIndex).getCodigoRec();
			listaRecibosCadastrados = new JList<String>(listaNomes);
			janela = new JFrame("Recibos");
			titulo = new JLabel("Recibos Cadastrados");
			refreshRecibo = new JButton("Refresh");
	
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(90, 10, 250, 30);
			listaRecibosCadastrados.setBounds(20, 50, 350, 120);
			listaRecibosCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaRecibosCadastrados.setVisibleRowCount(10);
	
			refreshRecibo.setBounds(200, 177, 100, 30);
	
			janela.setLayout(null);
	
			janela.add(titulo);
			janela.add(listaRecibosCadastrados);
			janela.add(refreshRecibo);
	
			janela.setSize(400, 250);
			janela.setVisible(true);
	
			refreshRecibo.addActionListener(this);
			listaRecibosCadastrados.addListSelectionListener(this);
		}
	}


	/**
	 * Captura eventos relacionados aos botoes da interface.
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		//Cadastro de novo Produto
		if(src == cadastroProd) {
			System.out.println("Cadastro Prod");
			new TelaDetalhes().inserirEditar(2, dados, 0);
		}
		
		//Cadastro de novo Cliente
		if(src == cadastroCliente) {
			System.out.println("Cadastro Cliente");
			new TelaDetalhes().inserirEditar(3, dados, 0);
		}
		
		// Atualiza a lista de nomes de Produtos mostrada no JList
		if(src == refreshProd) {
			listaProdsCadastrados.setListData(new ControleProduto(dados).getNomeProd());
			listaProdsCadastrados.updateUI();
		}
		
		// Atualiza a lista de nomes de Clientes mostrada no JList
		if(src == refreshCliente) {
			listaClientesCadastrados.setListData(new ControleCliente(dados).getNomeCliente());			
			listaClientesCadastrados.updateUI();
		}

		if(src == refreshRecibo) {
			listaRecibosCadastrados.setListData(new ControleRecibo(dados, cliIndex).getCodigoRec());			
			listaRecibosCadastrados.updateUI();
		}
	}

	/**
	 * Captura eventos relacionados ao JList. Ao selecionar um item,
	 * sera apresentada uma nova tela de detalhes.
	 */
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();

		if(e.getValueIsAdjusting() && src == listaProdsCadastrados) {
			new TelaDetalhes().inserirEditar(4, dados, 
					listaProdsCadastrados.getSelectedIndex());
		}
		
		
		if(e.getValueIsAdjusting() && src == listaClientesCadastrados) {
			new TelaDetalhes().inserirEditar(5, dados,
					listaClientesCadastrados.getSelectedIndex());
		}
		
		if(e.getValueIsAdjusting() && src == listaRecibosCadastrados) {
			new TelaDetalhes().inserirEditar(7, dados,
					listaRecibosCadastrados.getSelectedIndex(), cliIndex);
		}
	}

}

