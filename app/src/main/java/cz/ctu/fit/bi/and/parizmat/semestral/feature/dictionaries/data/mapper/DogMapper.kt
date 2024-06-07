package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.mapper

import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.dto.DogDto
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
/**
 * Extension function to convert a DogDto object to a Dog entity.
 *
 * @param groupName A function that accepts a group ID (as a String) and returns a nullable String,
 * representing the human-readable name of the group. If the group name is not found, it returns null,
 * and "Error" is used as the group name in the resulting Dog object.
 *
 * @return Dog Returns a Dog entity with properties mapped from the DogDto
 */
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
/**
 * Extension function to convert a Dog model to a DogEntity for database storage.
 *
 * @return DogEntity Returns a DogEntity object with properties derived from the Dog model.
 */
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
/**
 * Extension function to convert a DogEntity from the database into a Dog model.
 *
 * @return Dog Returns a Dog object reconstructed with all properties from the DogEntity.
 */
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
