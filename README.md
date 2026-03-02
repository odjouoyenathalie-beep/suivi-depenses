# 💰 Outil de Suivi des Dépenses Personnelles

> Projet de TP Java — Université de Natitingou (FAST) — 2025/2026

## 📋 Description

Application console en Java permettant de suivre, catégoriser et analyser ses dépenses personnelles.

## ✨ Fonctionnalités

- ✅ Ajouter une dépense (montant, date, catégorie, description)
- ✅ Afficher toutes les dépenses
- ✅ Modifier ou supprimer une dépense
- ✅ Définir un budget mensuel par catégorie
- ✅ Générer un rapport par catégorie (avec statut budget)
- ✅ Générer un rapport par mois
- ✅ Alertes automatiques en cas de dépassement de budget

## 🗂️ Structure du projet

```
suivi-depenses/
└── src/
    ├── Categorie.java       # Enumération des catégories
    ├── Depense.java         # Classe modèle d'une dépense
    ├── Budget.java          # Classe modèle d'un budget
    ├── GestionDepenses.java # Logique métier (ajout, modif, suppression, calculs)
    ├── Rapport.java         # Génération des rapports dans le terminal
    └── Main.java            # Point d'entrée, menu console
```

## 🚀 Comment exécuter

### Prérequis
- Java JDK 8 ou supérieur installé

### Compilation
```bash
cd src
javac *.java
```

### Exécution
```bash
java Main
```

## 📸 Exemple d'utilisation

```
╔══════════════════════════════════════════════╗
║   OUTIL DE SUIVI DES DEPENSES PERSONNELLES  ║
║         Université de Natitingou - FAST      ║
╚══════════════════════════════════════════════╝

┌─────────────────────────────────┐
│         MENU PRINCIPAL          │
├─────────────────────────────────┤
│  1. Ajouter une dépense         │
│  2. Afficher toutes les dépenses│
│  3. Modifier une dépense        │
│  4. Supprimer une dépense       │
│  5. Définir un budget           │
│  6. Rapports                    │
│  7. Quitter                     │
└─────────────────────────────────┘
```

## 👨‍💻 Auteur

Étudiant en Licence Informatique — FAST, Université de Natitingou, Bénin
