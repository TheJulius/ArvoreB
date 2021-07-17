package bTree;

public class BNode
{
	static int t;  //variavel que determina a orde da arvore

	int count; //numero de chaves em um nodo

	int key[];  //array do valor das chaves

	BNode child[]; //array de referencias

	boolean leaf; //se o nodo e uma folha ou nao

	BNode parent;  //pais do nodo atual

	public BNode(){}

	public BNode(int t, BNode parent)
	{
		this.t = t;  //designar o tamanho

		this.parent = parent; //designar o pai

		key = new int[2*t - 1];  //array do proprio tamanho atual

		child = new BNode[2*t]; //array de referencia do proprio tamanho

		leaf = true; //todo nodo comeca como uma folha

		count = 0; //ate adicionar outras chaves mais tarde
	}

	public int getValue(int index)
	{
		return key[index];
	}

	public BNode getChild(int index)
	{
		return child[index];
	}


}
