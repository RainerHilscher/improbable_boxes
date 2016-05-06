package improbable.physical

import improbable.papi.world.World
import improbable.papi.entity.{EntityBehaviour, Entity}
import improbable.papi.world.messaging.CustomMsg

import scala.concurrent.duration._

case object Ignite extends CustomMsg

class PropagateFireBehaviour(fire: FireWriter, world: World, entity: Entity) extends EntityBehaviour {

  if (fire.onFire) {
    ignite()
  }

  world.messaging.onReceive {
    case Ignite =>
      if (!fire.onFire) {
        ignite()
      }
  }

  def ignite(): Unit = {
    fire.update.onFire(true).finishAndSend()
    world.timing.every(500.milliseconds) {
      spreadFire()
    }
  }

  def spreadFire(): Unit = {
    world.entities.find(entity.position, 5.0f).foreach {
      otherEntity =>
        world.messaging.sendToEntity(otherEntity.entityId, Ignite)
    }
  }
}