package bTree;

public class BTree
{


	static int order; //ordenacao da arvore

	BNode root;  //toda arvore tem pelo menos um nodo raiz

	public BTree(int order)
	{
		this.order = order;
		root = new BNode(order, null);

	}

	public BNode search(BNode root, int key)
	{
		int i = 0;//sempre queremos comecar a busca no indice zero do nodo

		while(i < root.count && key > root.key[i])//continua incrementando no nodo enquanto a chava for maior que o valor atual
		{
			i++;
		}

		if(i <= root.count && key == root.key[i])//se a chave esta no nodo entao retorna o nodo
							                     
		{
			return root;
		}

		if(root.leaf)//ja que ja checamos a raiz, se for a folha nao tem muito o que checar
  			    
        {
			return null ;
		}
		else//caso nao, percorre ao proximo filho
		{
			return search(root.getChild(i),key);
		}
	}

	public void split(BNode x, int i, BNode y)
	{
		BNode z = new BNode(order,null);//precisa de um nodo extra em caso precise fazer a divisao
	                					

		z.leaf = y.leaf;//setar o mesmo valor que o y

		z.count = order - 1;//esse e o novo valor 

		for(int j = 0; j < order - 1; j++)
		{
			z.key[j] = y.key[j+order]; //copiar o final do y com o inicio do z

		}
		if(!y.leaf)//caso nao esteja na folha, tem que reordenar os nodos filhos
		{
			for(int k = 0; k < order; k++)
			{
				z.child[k] = y.child[k+order]; //reordenando os filhos de y
			}
		}

		y.count = order - 1; //novo tamanho de y

		for(int j = x.count ; j> i ; j--)//se puxar a chava para x, temos que reordenar os nodos filhos
		{
			x.child[j+1] = x.child[j]; // puxar o filho de x
		}
		
		x.child[i+1] = z; //redesignar i+1 para o filho de x

		for(int j = x.count; j> i; j--)
		{
			x.key[j + 1] = x.key[j];
		}
		x.key[i] = y.key[order-1];//finalmente puxar os valores para a raiz

		y.key[order-1 ] = 0; //apagar o valor de onde veio

		for(int j = 0; j < order - 1; j++)
		{
			y.key[j + order] = 0; //deletar os valores antigos
		}

		x.count ++;  //aumentar o contar de chaves no x
	}

	public void nonfullInsert(BNode x, int key)
	{
		int i = x.count; //i e o numero de chaves no nodo x

		if(x.leaf)
		{
			while(i >= 1 && key < x.key[i-1])//aqui acha o lugar para colocar a chave
			{
				x.key[i] = x.key[i-1];//puxa os valores para dar espaco

				i--;//decrementa
			}

			x.key[i] = key;//finalmente, designar os valores para o nodo
			x.count ++; //incrementar o contador de chaves para esse nodo

		}


		else
		{
			int j = 0;
			while(j < x.count  && key > x.key[j])//achar um lugar para um recurso no filho correto
			{			             		
				j++;
			}

			if(x.child[j].count == order*2 - 1)
			{
				split(x,j,x.child[j]);//chamar o metodo de dividir no nodo x

				if(key > x.key[j])
				{
					j++;
				}
			}

			nonfullInsert(x.child[j],key);//recurso
		}
	}

	public void insert(BTree t, int key)
	{
		BNode r = t.root;//esse metodo acha o nodo a ser inserido.
				 		 //comeca pelo nodo raiz.
		if(r.count == 2*order - 1)//se tiver cheio
		{
			BNode s = new BNode(order,null);//cria um nodo

			t.root = s;    //\
	    			       // \	
			s.leaf = false;//  \
	    			       //   > para iniciar o nodo.
			s.count = 0;   //  /
	    			       // /	
			s.child[0] = r;///

			split(s,0,r);//dividir a raiz

			nonfullInsert(s, key); //chamada para inserir o metodo
		}
		else
			nonfullInsert(r,key);//se nao tiver cheio, so insere e deu boa
	}

	public void print(BNode n)
	{
		for(int i = 0; i < n.count; i++)
		{
			System.out.print(n.getValue(i)+" " );//essa parte printa o nodo
		}

		if(!n.leaf)//esse so e chamado quando a raiz nao e uma folha
		{

			for(int j = 0; j <= n.count  ; j++)//in this loop we recurse nesse loop e para imprimir de uma forma pre ordenada
			{				  				   //indo do filho mais esquerda para o mais a direita 
				if(n.getChild(j) != null) 
				{			  
				System.out.println();
				print(n.getChild(j));
				}
			}
		}
	}

	public void SearchPrintNode( BTree T,int x)
	{
		BNode temp= new BNode(order,null);

		temp= search(T.root,x);

		if (temp==null)
		{

		System.out.println("A chave nao existe nesta árvore");
		}

		else
		{

		print(temp);
		}


	}

     public void deleteKey(BTree t, int key)
     {
			       
		BNode temp = new BNode(order,null);//nodo temporario
			
		temp = search(t.root,key);//chamada do metodo de busca para procurar uma chave

		if(temp.leaf && temp.count > order - 1)
		{
			int i = 0;

			while( key > temp.getValue(i))
			{
				i++;
			}
			for(int j = i; j < 2*order - 2; j++)
			{
				temp.key[j] = temp.getValue(j+1);
			}
			temp.count --;
		
		}
		else
		{
			System.out.println("Esse nodo não é nem uma folha ou tem menos ao menos 1 chave");
		}
     }


}
