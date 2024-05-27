package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.mapper

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog

fun DogDto.toDog(groupName: (String) -> String?): Dog {
    val group = groupName(relationships.group.data.id) ?: "Error"
    return Dog(
        id = id,
        name = attributes.name,
        description = attributes.description,
        hypoallergenic = attributes.hypoallergenic,
        life = Dog.Range(attributes.life.min, attributes.life.max),
        maleWeight = Dog.Range(attributes.life.min, attributes.life.max),
        femaleWeight = Dog.Range(attributes.life.min, attributes.life.max),
        group = group,
    )
}

fun Dog.toDogEntity(): DogEntity {
    return DogEntity(
        id = id,
        name = name,
        description = description,
        hypoallergenic = hypoallergenic,
        image = image,
        group = group,
        maleWeightMin = maleWeight.min,
        maleWeightMax = maleWeight.max,
        femaleWeightMin = femaleWeight.min,
        femaleWeightMax = femaleWeight.max,
        lifeMin = life.min,
        lifeMax = life.max,
    )
}

fun DogEntity.toDog(): Dog {
    return Dog(
        id = id,
        name = name,
        description = description,
        hypoallergenic = hypoallergenic,
        image = image,
        group = group,
        maleWeight = Dog.Range(maleWeightMin, maleWeightMax),
        femaleWeight = Dog.Range(femaleWeightMin, femaleWeightMax),
        life = Dog.Range(lifeMin, lifeMax),
    )
}
