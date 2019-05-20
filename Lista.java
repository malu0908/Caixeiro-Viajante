package Lista;

public class Lista {
	// top eh um atributo para o seu comeco e down para o seu fim
	public Node top;
	public Node down;

	public void clonar(Lista l) {
		Node atual = l.top;
		int i = 0;
		while(atual != null) {
			this.insereIndice(atual.value, i);
			atual = atual.next;
			i++;
		}
	}
	
	public void clean() {
		Node atual = this.top;
		while(atual != null) {
			this.removePorIndice(0);
			atual = this.top;
		}
	}
	
	public Node buscaValor(int indice) {
		Node atual = this.top;
		int i = 0;
		while(atual != null) {
			if(indice == i) {
				return atual;
			}
			i++;
			atual = atual.next;
		}
		return null;
	}
	
	// Insere na lista
	public void toList(int l) {
		// instancia um objeto Node na memoria chamado newNode
		Node newNode = new Node(l, null, null);
		// Se a lista for vazia
		if (this.top == null) {
			this.top = newNode;
			this.down = this.top;
			this.top.value = l;
			this.top.next = null;
			this.down.previous = null;
		} else {
			Node atual;
			atual = this.top;

			// Percorre a lista ate encontrar um valor maior que o valor de l ou o proximo
			// do atual for null
			while (l > atual.value && atual.next != null) {
				atual = atual.next;
			}
			// Se estiver tentando inserir um valor repetido
			if (atual.value == l) {
				return;
			} else {
				newNode.value = l;

				if (l < atual.value) {
					// Insere no inicio da lista
					if (atual == this.top) {
						this.top = newNode;
						newNode.previous = null;
					} else { // Insere no meio da lista
						newNode.previous = atual.previous;
						newNode.previous.next = newNode;
					}
					newNode.next = atual;
					atual.previous = newNode;
				} else { // insere no fim da lista
					this.down = newNode;
					atual.next = newNode;
					newNode.previous = atual;
					newNode.next = null;
				}
			}
		}
	}
		
	public void insereIndice(int valor, int indice) {
		Node newNode = new Node(valor, null, null);
		if(this.top == null && indice == 0) {
			this.top = newNode;
			this.down = newNode;
		}
		else if(this.top != null) {
			int i = 0;
			Node atual = this.top;
			
			while(atual != null) {
				if(atual.next == null && i == indice-1) {
					atual.next = newNode;
					newNode.previous = atual;
					this.down = newNode;
					break;
				}
				if(i == indice) {
					newNode.next = atual;
					if(atual.previous != null) {
						atual.previous.next = newNode;
						newNode.previous = atual.previous;
					}
					atual.previous = newNode;
					if(indice == 0)
						this.top = newNode;
					break;
				}
				i++;
				atual = atual.next;
			}
		}
	}

	public Node removeList(int valor) {
		Node atual = this.top;

		while (atual != null) {
			if (atual.value == valor) {
				if (atual == this.top) {
					if (this.top == this.down) {
						this.top = null;
						this.down = null;
					} else {
						this.top = atual.next;
						atual.next.previous = null;
					}
				} else {
					atual.previous.next = atual.next;
					if (atual != this.down) {
						atual.next.previous = atual.previous;
					} else {
						this.down = atual.previous;
					}
				}
				atual.previous = null;
				atual.next = null;

				return atual;
			}
			atual = atual.next;
		}
		return null;
	}
	
	public Node removePorIndice(int indice) {
		Node atual = this.top;

		int i = 0;
		while (atual != null) {
			if (i == indice) {
				if (atual == this.top) {
					if (this.top == this.down) {
						this.top = null;
						this.down = null;
					} else {
						this.top = atual.next;
						atual.next.previous = null;
					}
				} else {
					atual.previous.next = atual.next;
					if (atual != this.down) {
						atual.next.previous = atual.previous;
					} else 
						this.down = atual.previous;
				}
				atual.previous = null;
				atual.next = null;

				return atual;
			}
			i++;
			atual = atual.next;
		}
		return null;
	}
	
	public void escreve() {
		Node atual = this.top;
		int i = 1;
		while(atual != null) {
			System.out.print(atual.value + " --> ");
			atual = atual.next;
			i++;
		}
	}
}