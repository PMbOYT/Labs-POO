using System;
using System.Collections.Generic;

public abstract class EntitateEcosistem
{
    protected string nume;
    protected int energie;
    protected (int x, int y) pozitie;
    protected double rataSupravietuire;

    public EntitateEcosistem(string nume, int energie, (int, int) pozitie, double rataSupravietuire)
    {
        this.nume = nume;
        this.energie = energie;
        this.pozitie = pozitie;
        this.rataSupravietuire = rataSupravietuire;
    }

    public abstract void actioneaza();
}

public class Planta  EntitateEcosistem
{
    public Planta(string nume, int energie, (int, int) pozitie)
         base(nume, energie, pozitie, 1.0)
    { }

    public override void actioneaza()
    {
        Console.WriteLine(${nume} crește.);
    }
}

public abstract class Animal  EntitateEcosistem
{
    protected int viteza;
    protected string tipHrana;

    public Animal(string nume, int energie, (int, int) pozitie, int viteza, string tipHrana)
         base(nume, energie, pozitie, 0.8)
    {
        this.viteza = viteza;
        this.tipHrana = tipHrana;
    }

    public abstract void mananca();
    public abstract void deplaseaza();
}

public class Erbivor  Animal
{
    public Erbivor(string nume, int energie, (int, int) pozitie, int viteza)
         base(nume, energie, pozitie, viteza, plante) { }

    public override void mananca()
    {
        Console.WriteLine(${nume} mănâncă plante.);
    }

    public override void deplaseaza()
    {
        Console.WriteLine(${nume} se deplasează.);
    }

    public override void actioneaza()
    {
         Erbivor specific action
        Console.WriteLine(${nume} acționează se deplasează și mănâncă plante.);
    }
}

public class Carnivor  Animal
{
    public Carnivor(string nume, int energie, (int, int) pozitie, int viteza)
         base(nume, energie, pozitie, viteza, animale) { }

    public override void mananca()
    {
        Console.WriteLine(${nume} vânează alte animale.);
    }

    public override void deplaseaza()
    {
        Console.WriteLine(${nume} se deplasează mai rapid.);
    }

    public override void actioneaza()
    {
         Carnivor specific action
        Console.WriteLine(${nume} acționează vânează și se deplasează.);
    }
}

public interface IInteractiune
{
    void ataca(Animal prada);
    void reproduce();
}

public class Ecosistem
{
    private ListEntitateEcosistem entitati;

    public Ecosistem()
    {
        entitati = new ListEntitateEcosistem();
    }

    public void adaugaEntitate(EntitateEcosistem entitate)
    {
        entitati.Add(entitate);
    }

    public void simuleazaPas()
    {
        foreach (var entitate in entitati)
        {
            entitate.actioneaza();
        }
    }

    public void afiseazaEcosistem()
    {
        Console.WriteLine(Starea ecosistemului);
        foreach (var entitate in entitati)
        {
            Console.WriteLine($Entitate {entitate.GetType().Name}, Nume {entitate.ToString()});
        }
    }
}

public class Program
{
    public static void Main()
    {
         Crearea ecosistemului
        Ecosistem ecosistem = new Ecosistem();

         Adăugăm plante și animale
        Planta planta = new Planta(Planta1, 10, (5, 5));
        Erbivor erbivor = new Erbivor(Iepure, 20, (1, 1), 5);
        Carnivor carnivor = new Carnivor(Lup, 40, (2, 2), 8);

        ecosistem.adaugaEntitate(planta);
        ecosistem.adaugaEntitate(erbivor);
        ecosistem.adaugaEntitate(carnivor);

         Simulăm un pas
        ecosistem.simuleazaPas();

         Afisam starea ecosistemului
        ecosistem.afiseazaEcosistem();
    }
}
