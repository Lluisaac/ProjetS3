package pathfinding;

import java.util.ArrayList;
import java.util.List;

public class Algo {
	Topo topo;
	int[] d; //depart
	int[] a; //arrivée;
	Case c; //current
	List<Case> open = new ArrayList<Case>(); // cases à traiter
	List<Case> close = new ArrayList<Case>(); // cases traitées
	
	public Algo(Topo topo,int[] d,int[] a) {
		this.topo = topo;
		this.d = d;
		this.a = a;
	};
	
	 
	public void Path(){
		
		this.topo.find(d[0], d[1]).x = d[0];
		this.topo.find(d[0], d[1]).y = d[1];
		this.topo.find(d[0], d[1]).F = Math.abs(d[0] - a[0]) + Math.abs(d[1] - a[1]);
		this.open.add(this.topo.find(d[0], d[1]));
		this.c = this.topo.find(d[0], d[1]);
		int minF = 0;
		int IDminF = 0;
		boolean CheminTrouve = false;
		int multiplieurH = 1;
		int tourDeTest = 0;
		
		
		

		
		
		while(!this.open.isEmpty() && !CheminTrouve){
			
			tourDeTest++; // pour compter le nombre de tour de l'algo
			
		    for(int i = 0; i <this.open.size() ; i++){  // on prend la case avec le F minimum
		    	this.c = this.open.get(i);

		    	if(minF == 0 || this.open.get(i).F < minF){
		    		minF = this.topo.find(this.c.x,this.c.y).F;
		    		IDminF = i;

		    	}
		    	
		    }
		    this.c = this.open.get(IDminF); // et on la stock dans c
			minF = 0;
			IDminF = 0;
			

			if(this.c.x != 0 && !this.close.contains(this.topo.find(this.c.x-1,this.c.y)) && Math.abs(this.c.niv - this.topo.find(this.c.x-1,this.c.y).niv)<10 ){ //si la case ne dépasse pas de la matrice et n'est pas dans la close list et est accessible
				if( this.open.contains(this.topo.find(this.c.x-1,this.c.y)) && this.topo.find(this.c.x-1,this.c.y).G < this.c.G + 1){ // si elle est deja dans l'open list et que son G est plus interessant que c.G +1 alors il existe deja un chemin pour cette case plus optimisé

				}else{
					this.open.remove(this.topo.find(this.c.x-1,this.c.y)); // on enlève la case de l'open list car on recalcule ses variables
					this.topo.find(this.c.x-1,this.c.y).x = this.c.x-1;
					this.topo.find(this.c.x-1,this.c.y).y = this.c.y;
					this.topo.find(this.c.x-1,this.c.y).parent = this.c;
					this.topo.find(this.c.x-1,this.c.y).G =  this.c.G + 1;
			    	this.topo.find(this.c.x-1,this.c.y).H = (Math.abs(this.c.x-1 - a[0]) + Math.abs(this.c.y - a[1])) * multiplieurH;
			    	this.topo.find(this.c.x-1,this.c.y).F = this.topo.find(this.c.x-1,this.c.y).G + this.topo.find(this.c.x-1,this.c.y).H;
					this.open.add(this.topo.find(this.c.x-1,this.c.y));
				}
			}
			if(this.c.y != 0 && !this.close.contains(this.topo.find(this.c.x,this.c.y-1)) && Math.abs(this.c.niv - this.topo.find(this.c.x,this.c.y-1).niv)<10 ){
				if( this.open.contains(this.topo.find(this.c.x,this.c.y-1)) && this.topo.find(this.c.x,this.c.y-1).G < this.c.G + 1){

				}else{
					this.open.remove(this.topo.find(this.c.x,this.c.y-1));
					this.topo.find(this.c.x,this.c.y-1).x = this.c.x;
					this.topo.find(this.c.x,this.c.y-1).y = this.c.y-1;
					this.topo.find(this.c.x,this.c.y-1).parent = this.c;
					this.topo.find(this.c.x,this.c.y-1).G =  this.c.G + this.topo.find(this.c.x,this.c.y-1).niv;
			    	this.topo.find(this.c.x,this.c.y-1).H = (Math.abs(this.c.x - a[0]) + Math.abs(this.c.y-1 - a[1])) * multiplieurH;
			    	this.topo.find(this.c.x,this.c.y-1).F = this.topo.find(this.c.x,this.c.y-1).G + this.topo.find(this.c.x,this.c.y-1).H;
					this.open.add(this.topo.find(this.c.x,this.c.y-1));

				}
			}
			if(this.c.x != this.topo.largeur-1 && !this.close.contains(this.topo.find(this.c.x+1,this.c.y))&& Math.abs(this.c.niv - this.topo.find(this.c.x+1,this.c.y).niv)<10){
				if( this.open.contains(this.topo.find(this.c.x+1,this.c.y)) && this.topo.find(this.c.x+1,this.c.y).G < this.c.G + 1){

				}else{
					this.open.remove(this.topo.find(this.c.x+1,this.c.y));
					this.topo.find(this.c.x+1,this.c.y).x = this.c.x+1;
					this.topo.find(this.c.x+1,this.c.y).y = this.c.y;
					this.topo.find(this.c.x+1,this.c.y).parent = this.c;
					this.topo.find(this.c.x+1,this.c.y).G =  this.c.G + this.topo.find(this.c.x+1,this.c.y).niv;
			    	this.topo.find(this.c.x+1,this.c.y).H = (Math.abs(this.c.x+1 - a[0]) + Math.abs(this.c.y - a[1])) * multiplieurH;
			    	this.topo.find(this.c.x+1,this.c.y).F = this.topo.find(this.c.x+1,this.c.y).G + this.topo.find(this.c.x+1,this.c.y).H;
					this.open.add(this.topo.find(this.c.x+1,this.c.y));

				}
			}
			if(this.c.y != this.topo.hauteur-1 && !this.close.contains(this.topo.find(this.c.x,this.c.y+1))&& Math.abs(this.c.niv - this.topo.find(this.c.x,this.c.y+1).niv)<10){
				if( this.open.contains(this.topo.find(this.c.x,this.c.y+1)) && this.topo.find(this.c.x,this.c.y+1).G < this.c.G + 1){

				}else{
					this.open.remove(this.topo.find(this.c.x,this.c.y+1));
					this.topo.find(this.c.x,this.c.y+1).x = this.c.x;
					this.topo.find(this.c.x,this.c.y+1).y = this.c.y+1;
					this.topo.find(this.c.x,this.c.y+1).parent = this.c;
					this.topo.find(this.c.x,this.c.y+1).G =  this.c.G + this.topo.find(this.c.x,this.c.y+1).niv;
			    	this.topo.find(this.c.x,this.c.y+1).H = (Math.abs(this.c.x - a[0]) + Math.abs(this.c.y+1 - a[1])) * multiplieurH;
			    	this.topo.find(this.c.x,this.c.y+1).F = this.topo.find(this.c.x,this.c.y+1).G + this.topo.find(this.c.x,this.c.y+1).H;
					this.open.add(this.topo.find(this.c.x,this.c.y+1));
				}
			}
			
			if(this.c.x == a[0] && this.c.y == a[1]){       // cas d'arrêt
				CheminTrouve = true;
			}else{
				this.open.remove(this.c);
				this.close.add(this.c);
				
			}
			
		} //fin while
		
		if(CheminTrouve){
			List<Case> optimal = new ArrayList<Case>();
			
			while(this.c.x != d[0] || this.c.y != d[1]){   // retourner et stocker le chemin dans la liste "optimal"
				optimal.add(0, this.c);
				this.c = this.c.parent;
			}
		
	    	System.out.println("Chemin optimal :  ");
	    	System.out.println("[" + d[0] + d[1] +"]" + " -> ");
			for (Case n : optimal) {
		    	System.out.println("[" + n.x + n.y +"]" + " -> ");

			}
	    	System.out.println("arrivée ! (longueur du chemin : " + optimal.size() + " )");
	    	System.out.println("Pour ce résultat l'algo à fait " + tourDeTest + " tests");

		}else{
	    	System.out.println("aucun chemin ne mène au point d'arrivée");

		}
		
	}
	
}
