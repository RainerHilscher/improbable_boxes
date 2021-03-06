package improbable.behaviours.player.controls

import improbable.corelib.util.EntityOwnerDelegation.entityOwnerDelegation
import improbable.papi.entity.{Entity, EntityBehaviour}
import improbable.player.LocalPlayerCheckState

class DelegateLocalPlayerCheckToOwnerBehaviour(entity: Entity) extends EntityBehaviour {

  override def onReady(): Unit = {
    entity.delegateStateToOwner[LocalPlayerCheckState]
  }

}
