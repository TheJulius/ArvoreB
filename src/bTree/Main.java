package bTree;

import java.util.Scanner;
public class Main 
{

	public static void main(String[] args)
	{
        Scanner input = new Scanner( System.in );
		int n,n2,temp;
		System.out.print("Adicione a ordem da árvore: ");
		n=input.nextInt();
		
        while ( n<2)
		{	
            System.out.print("Por favor, adicionar um valor maior que 1 : ");
			n=input.nextInt();//N e a ordem da arvore adicionada pelo ususario
		}
		BTree tree = new BTree(n);//Arvore de Ordem N criada

        // Valores iniciais adicionados a arvore, pode adicionar qualquer valor inteiro.
		System.out.print("\n Quantos valores gostaria de adicionar? :  ");	
        n2 = input.nextInt();

        for ( int i=0;i< n2;i++)
		{
            System.out.print("\n Insira o valor:");
			System.out.println(i+1);
			temp=input.nextInt();
			tree.insert(tree,temp);
		}
		int choice,k;//Variaveis criadas para controlar a repeticao do menu.

        boolean flag;
		flag=true;
			System.out.println("MENU\n");
			System.out.println("1. Adicionar mais valores a árvore");
			System.out.println("2. Mostrar a arvore pre-ordenada");
			System.out.println("3. Procurar por uma chave e Mostrar a qual nodo pertence");
			System.out.println("4. Apagar a chave de uma folha");
			System.out.println("5. Sair");

		while (flag)// Esse loop vai rodar enquanto o usuario digitar qualquer coisa diferente de 5.
		{


			System.out.print("\nPor favor, digite sua escolha: ");
			choice=input.nextInt();
			if ( choice == 5)
			{		
                System.out.printf("Programa finalizado com sucesso...");
			    System.exit(0);
				flag=false;
				break;
			}
		
            else
			{
			 	switch(choice)
			 	{
			 		case 1: //Se o usuario inserir 1, esse caso e executado
                            //Essa funcao e para adicionar mais valores na arvore.
	
                        System.out.print("Quantos valores gostaria de adicionar?:");
						n2=input.nextInt();

                        for ( int i=0;i< n2;i++)
						{
                            System.out.print("\nAdicione os valores: ");
							System.out.println(i+1);
							temp=input.nextInt();
							tree.insert(tree,temp);
                        }
                        break;

					case 2: //Se o usuario inserir 2, esse caso e executado
                            //Esse caso mostra toda a arvore no formato pre-ordenado.
						
                        tree.print(tree.root);
						System.out.println();
						break;

					case 3: //Se o usuario inserir 3, esse caso e executado
                            //Esse caso e para buscar uma chave de uma folha
		
                        System.out.println("Insira a chave que deseja procurar:");
						int key2=input.nextInt();
						tree.SearchPrintNode(tree,key2);

						break;
					case 4: //Se o usuario inserir 4, esse caso e executado
                            //Esse caso e para deletar a chave e mostrar a arvore pre-ordenada depois do delete
						
                        System.out.println("Insira a chave que deseja apagar:");
						int key=input.nextInt();
						tree.deleteKey(tree,key);
						System.out.println("Esta é a árvore reordenada após o delete");
						tree.print(tree.root);
						break;

					case 5: //Se o usuario inserir 3, esse caso e executado
                            //Esse caso e para sair do menu e fechar o sistema 
			
                        System.exit(0);
						break;

						default: // Se o usuario inserir um valor invalido, mostra essa mensagem.
						System.out.println("\nPor favor, insira um valor valido entre 1,2,3,4 ou 5 para sair\n");
						break;
	 			}//final do switch 
			 }//final do else
		}//final do while
	}//main
}//classe
