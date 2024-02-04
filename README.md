#  Android Lydia Test App
Cette application contient tous les éléments qui fourniront une base solide pour le
application plus grande adaptée aux grandes équipes et étendue.

## Architecture

En divisant un problème en sous-problèmes plus petits et plus faciles à résoudre, nous pouvons réduire la complexité de la conception et
maintenir un grand système. Chaque module est un bloc de construction indépendant servant un objectif clair. Nous pouvons penser à chacun
fonctionnalité en tant que composant réutilisable, équivalent ou bibliothèque privée.

Application multimodulaire + MVVM

## Dependences

[versions catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog)


## CI Pipeline

[GitHub Actions](https://github.com/features/actions)

### Pull Request Verification

* `./gradlew lintDebug`
* `./gradlew detektCheck`
* `./gradlew detektApply`
* `./gradlew spotlessCheck`
* `./gradlew spotlessApply`
* `./gradlew testDebugUnitTest`
* `./gradlew :app:bundleDebug`


## Tests

Des tests basiques ont été faits. Pas de temps pour des tests plus poussés