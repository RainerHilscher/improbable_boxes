package improbable.apps

import improbable.math.Vector3d
import improbable.natures.BotNature
import improbable.papi.world.AppWorld
import improbable.papi.worldapp.WorldApp

import scala.concurrent.duration._
import scala.language.postfixOps

class CubeSpawner(appWorld: AppWorld) extends WorldApp {

  spawnCubes()
  spawnFireCubes()

  private def spawnCubes(): Unit = {
    Range.inclusive(1, 500).foreach {
      i =>
        appWorld.timing.after((100 * i) millis) {
          appWorld.entities.spawnEntity(BotNature(Vector3d.unitY * 20.0f + Vector3d.unitX * 10.0f, onFire = false))
        }
    }
  }

  private def spawnFireCubes(): Unit = {
    Range.inclusive(1, 5).foreach {
      i =>
        appWorld.timing.after((500 * i) millis) {
          appWorld.entities.spawnEntity(BotNature(Vector3d.unitY * 30.0f + Vector3d.unitX * 5.0f, onFire = true))
        }
    }
  }

}
