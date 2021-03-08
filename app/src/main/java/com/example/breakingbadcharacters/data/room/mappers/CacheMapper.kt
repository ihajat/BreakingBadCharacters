package com.example.breakingbadcharacters.data.room.mappers

import com.example.breakingbadcharacters.data.network.mappers.EntityMapper
import com.example.breakingbadcharacters.data.room.model.CharacterEntity
import com.example.breakingbadcharacters.domain.model.BBCharacter
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<CharacterEntity, BBCharacter> {

    override fun mapFromEntity(entity: CharacterEntity): BBCharacter {
        return BBCharacter(
            char_id = entity.char_id,
            name = entity.name,
            birthday = entity.birthday,
            occupation = entity.occupation,
            img = entity.img,
            status = entity.status,
            nickname = entity.nickname,
            appearance = entity.appearance,
            portrayed = entity.portrayed,
            category = entity.category,
            better_call_saul_appearance = entity.better_call_saul_appearance
        )
    }

    override fun mapToEntity(domainModel: BBCharacter): CharacterEntity {
        return CharacterEntity(
            char_id = domainModel.char_id,
            name = domainModel.name,
            birthday = domainModel.birthday,
            occupation = domainModel.occupation,
            img = domainModel.img,
            status = domainModel.status,
            nickname = domainModel.nickname,
            appearance = domainModel.appearance,
            portrayed = domainModel.portrayed,
            category = domainModel.category,
            better_call_saul_appearance = domainModel.better_call_saul_appearance,
        )
    }

    fun mapFromEntityList(entities: List<CharacterEntity>): List<BBCharacter> {
        return entities.map { mapFromEntity(it) }
    }
}


