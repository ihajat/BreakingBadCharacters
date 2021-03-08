package com.example.breakingbadcharacters.data.network.mappers

import com.example.breakingbadcharacters.data.network.model.CharacterDto
import com.example.breakingbadcharacters.domain.model.BBCharacter
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<CharacterDto, BBCharacter> {

    override fun mapFromEntity(entity: CharacterDto): BBCharacter {
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

    override fun mapToEntity(domainModel: BBCharacter): CharacterDto {
        return CharacterDto(
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

    fun mapFromEntityList(entities: List<CharacterDto>): List<BBCharacter> {
        return entities.map { mapFromEntity(it) }
    }

}

